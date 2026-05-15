package com.example.libraryservice.controller;

import com.example.libraryservice.dto.BookDTO;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.service.DigitalResourceService;
import com.example.libraryservice.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private DigitalResourceService digitalResourceService;
    @Autowired
    private LibraryService libraryService;
    @PostMapping("/book")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add book here")
    public String addBook(@RequestBody BookDTO dto) {
        return libraryService.addBook(dto);
    }

    @PostMapping(value = "/pdf",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add digital resources like pdfs,videos here")
    public String uploadPDF(@RequestParam String courseCode, @RequestParam("file")MultipartFile file) throws IOException {
        return digitalResourceService.uploadPDF(courseCode,file);
    }
    @PostMapping(value = "/video",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add digital resources like pdfs,videos here")
    public String uploadVideo(@RequestParam String courseCode, @RequestParam("file")MultipartFile file) throws IOException {
        return digitalResourceService.uploadVideo(courseCode,file);
    }
    @GetMapping(value = "/digitalResource",consumes = "multipart/form-data")
    @Operation(summary = "anyone can get digital resources like pdfs,videos to their registered email  here")
    public String getResources(@RequestParam String courseCode){
        return digitalResourceService.getResourcesOfCourse(courseCode);
    }
    @GetMapping("/books")
    @Operation(summary = "anyone can get all books of a department  here")
    public List<Book> getBooksOfDept(@RequestParam String deptCode){
        return libraryService.getAllBooksInDepartment(deptCode);
    }
    @PostMapping("/borrow")
    @Operation(summary = "anyone can borrow book here")
    public String borrowBook(@RequestParam UUID studentId, @RequestParam String bookName, @RequestParam String courseCode, @RequestParam String deptCode) {
        return libraryService.borrowBook(studentId,bookName,courseCode,deptCode);
    }
    @PutMapping("/return")
    @Operation(summary = "those who borrowed book should return here")
    public String returnBook(@RequestParam UUID studentId, @RequestParam String bookName) {
        return libraryService.returnBook(studentId,bookName);
    }

}
