package com.example.springbootlibrary.entity.controller;


import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook (@RequestParam Long bookId) throws Exception {
        String userEmail = "test@email.com";
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkBookByUser(@RequestParam Long bookId) {
        String userEmail = "test@email.com";
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(){
        String userEmail = "test@email.com";
        return bookService.currentLoansCount(userEmail);
    }
}
