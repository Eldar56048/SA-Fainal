package com.emeka.bookshop_inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        Book book = bookRepository.getAllById(id);
        return book;
    }

    @PostMapping("/books")
    public String createBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book created successfully";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
        return "Book "+id+" Successfully deleted";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@PathVariable int id,@RequestBody Book newBook){
        Book book = bookRepository.getAllById(id);
        book.setAuthor(newBook.getAuthor());
        book.setGenre(newBook.getGenre());
        book.setName(newBook.getName());
        bookRepository.save(book);
        return "Book "+id+" Successfully updated";
    }
}
