package com.example.librarymanagement.service;

import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.User;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow(()-> new  RuntimeException("The Book Not Found"));
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("The Book Not Found"));
        bookRepository.delete(book);
    }

    public Book borrowedBook(long bookId,long userId){
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if(book!=null && !book.isBorrowed() && user!=null){
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            return saveBook(book);

        }else throw  new RuntimeException("You Can't Borrow This Book Now");
    }

    public Book returnBook(long bookId){

        Book book = findById(bookId);

        if(book!=null&&book.isBorrowed()){
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return saveBook(book);
        }else throw new RuntimeException("You Can't Return This Book Now");
    }


}
