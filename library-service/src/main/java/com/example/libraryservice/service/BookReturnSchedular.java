package com.example.libraryservice.service;

import com.example.events.BookReturnAlert;
import com.example.events.LowAttendanceAlert;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.entity.BookRecords;
import com.example.libraryservice.repository.BookRecordRepoistory;
import com.example.libraryservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookReturnSchedular {
    @Autowired
    private BookRecordRepoistory recordRepoistory;
    @Autowired
    private BookRepository bookRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookReturnSchedular(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void bookReturnAlert() {
        List<BookRecords> b = recordRepoistory.findAll();
        for (BookRecords records : b) {
            Book book = bookRepository.findById(records.getBookId()).orElse(new Book());
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), records.getReturnDate());
            if (daysLeft == 1 || daysLeft == 2 || daysLeft == 3) {
                BookReturnAlert dto = new BookReturnAlert(records.getStudentId(), records.getReturnDate(), records.getIssueDate(), records.getFine(), book.getBookName(),daysLeft);
                kafkaTemplate.send("bookReturnAlert", dto);
            }

        }
    }
}
