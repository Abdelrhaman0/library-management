package com.example.librarymanagement.rest;

import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId,@PathVariable Long userId){

        Book borrowBook = bookService.borrowedBook(bookId,userId);

        if(borrowBook!=null){

            return ResponseEntity.ok(borrowBook);

        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId){

        Book returnBook = bookService.returnBook(bookId);
        if (returnBook!=null){

            return ResponseEntity.ok(returnBook);

        }else return ResponseEntity.badRequest().build();
        }

    }








