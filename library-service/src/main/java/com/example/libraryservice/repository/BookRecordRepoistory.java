package com.example.libraryservice.repository;

import com.example.libraryservice.entity.BookRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRecordRepoistory extends JpaRepository<BookRecords,Long> {
    BookRecords findByStudentIdAndBookId(UUID studentId, Long bookId);
}
