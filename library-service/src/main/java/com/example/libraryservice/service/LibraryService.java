package com.example.libraryservice.service;

import com.example.libraryservice.dto.BookDTO;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.entity.BookRecords;
import com.example.libraryservice.entity.BookStatus;
import com.example.libraryservice.repository.BookRecordRepoistory;
import com.example.libraryservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LibraryService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookRecordRepoistory bookRecordRepoistory;
    public List<Book> getAllBooksInDepartment(String deptCode){
        return repository.findAllByDeptCode(deptCode);
    }

    public String addBook(BookDTO dto){
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
                return "this book is not avilable currently";
            }
        }
         return " ";
      }
    public String returnBook(UUID studentId,String bookName){
        Book b=repository.findByBookName(bookName);
        BookRecords record=bookRecordRepoistory.findByStudentIdAndBookId(studentId,b.getId());
        if(b.getCourseCode().equalsIgnoreCase(coursecode)&&b.getDeptCode().equalsIgnoreCase(deptCode)){
            if(b.getCopies()>0&&b.getStatus().equals(BookStatus.AVAILABLE)){
                b.setCopies(b.getCopies()-1);
                List<UUID> students=b.getStudentIds();
                students.add(studentId);
                b.setStudentIds(students);
                if(b.getCopies()-1==0){
                    b.setStatus(BookStatus.BORROWED);
                }
                else {
                    b.setStatus(BookStatus.AVAILABLE);
                }
                repository.save(b);
                return "book borrowed successfully";
            }
            else{
                return "this book is not avilable currently";
            }
        }
        return " ";
    }

}
