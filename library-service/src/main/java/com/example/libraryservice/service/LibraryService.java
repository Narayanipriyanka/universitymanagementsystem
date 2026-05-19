package com.example.libraryservice.service;

import com.example.events.BookCollectEvent;
import com.example.events.PayFeesEvent;
import com.example.libraryservice.dto.BookDTO;
import com.example.libraryservice.entity.*;
import com.example.libraryservice.repository.BookRecordRepoistory;
import com.example.libraryservice.repository.BookRepository;
import com.example.libraryservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class LibraryService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookRecordRepoistory bookRecordRepoistory;
    @Autowired
    private ReservationRepository reservationRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public LibraryService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Book> getAllBooksInDepartment(String deptCode){
        return repository.findAllByDeptCode(deptCode);
    }

    public String addBook(BookDTO dto){
        Book book1=repository.findByBookName(dto.getBookName());
        if(book1!=null&& Objects.equals(dto.getAuthor(), book1.getAuthor())){
             book1.setCopies(book1.getCopies()+1);
             repository.save(book1);
             return "already book present this is added as another copy of that book";
        }
        Book book=new Book();
        book.setBookName(dto.getBookName());
        book.setAuthor(dto.getAuthor());
        book.setCopies(dto.getCopies());
        book.setCourseCode(dto.getCourseCode());
        book.setDeptCode(dto.getDeptCode());
        book.setStatus(BookStatus.AVAILABLE);
        book.setMrp(dto.getMrp());
        repository.save(book);
        return "book added successfully";

    }
    public String borrowBook(UUID studentId,String bookName,String coursecode,String deptCode){
        Book b=repository.findByBookName(bookName);
        if(b.getCourseCode().equalsIgnoreCase(coursecode)&&b.getDeptCode().equalsIgnoreCase(deptCode)){
            if(b.getCopies()>0&&b.getStatus().equals(BookStatus.AVAILABLE)){
                b.setCopies(b.getCopies()-1);
                List<UUID> students=b.getStudentIds();
                students.add(studentId);
                b.setStudentIds(students);
                BookRecords records=new BookRecords();
                records.setStudentId(studentId);
                records.setBookId(b.getId());
                records.setReturned(false);
                records.setIssueDate(LocalDate.now());
                records.setReturnDate(LocalDate.now().plusWeeks(1));
                records.setFine(b.getMrp()*10/100);
                bookRecordRepoistory.save(records);
                if(b.getCopies()-1==0){
                    b.setStatus(BookStatus.BORROWED);
                }
                else {
                    b.setStatus(BookStatus.AVAILABLE);
                }
                repository.save(b);
                return "book borrowed successfully your book id is"+b.getId() +"preserve this id for return";
            }
            else{
                List<Reservation> reserves=reservationRepository.findAllByBookName(b.getBookName());
                int lastPosition=0;
                for(Reservation reser:reserves){
                    if(reser.getPosition()>lastPosition){
                    lastPosition= reser.getPosition();}
                }
                Reservation r=new Reservation();
                r.setStudentId(studentId);
                r.setBookName(bookName);
                r.setPosition(lastPosition+1);
                r.setStatus(ReservationStatus.WAITING);
                r.setReserveDate(LocalDate.now());
                reservationRepository.save(r);
                return "this book is not available currently but the book is reserved to you once it is available based on your position in waitingList, you're position is"+r.getPosition();
            }
        }
         return " ";
      }
    public String returnBook(UUID studentId,String bookName){
        Book b=repository.findByBookName(bookName);
        BookRecords record=bookRecordRepoistory.findByStudentIdAndBookId(studentId,b.getId());
        if(Objects.equals(b.getId(), record.getBookId()) &&b.getStudentIds().contains(studentId)){
            if(LocalDate.now().isBefore(record.getReturnDate())){
                List<UUID> ids=b.getStudentIds();
                ids.remove(studentId);
                b.setStudentIds(ids);
                b.setCopies(b.getCopies()+1);
                b.setStatus(BookStatus.AVAILABLE);
                repository.save(b);
                record.setReturned(true);
                record.setReturnDate(LocalDate.now());
                List<Reservation> reserves=reservationRepository.findAllByBookName(b.getBookName());
                if(!reserves.isEmpty()){
                int firstPosition=Integer.MAX_VALUE;
                for(Reservation reser:reserves){
                    if(reser.getPosition()<firstPosition&&reser.getPosition()!=0){
                        firstPosition= reser.getPosition();}
                }
                Reservation reservation=reservationRepository.findByPosition(firstPosition);
                reservation.setPosition(0);
                reservation.setStatus(ReservationStatus.COLLECTED);
                BookCollectEvent dto=new BookCollectEvent(reservation.getStudentId(),reservation.getBookId(),reservation.getBookName());
                kafkaTemplate.send("bookIsGivenToYou",dto);}
                return "book return successfully";
            }
            else{
                List<UUID> ids=b.getStudentIds();
                ids.remove(studentId);
                b.setStudentIds(ids);
                b.setCopies(b.getCopies()+1);
                b.setStatus(BookStatus.AVAILABLE);
                repository.save(b);
                record.setReturned(true);
                record.setReturnDate(LocalDate.now());
                PayFeesEvent feesEvent=new PayFeesEvent(studentId,record.getFine(),"LIBRARY_FEE");
                kafkaTemplate.send("payFees",feesEvent);
                return "pay "+record.getFine()+" for late return ";
            }
        }
        return " ";
    }



}
