package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Book CRUD
    //Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //recuperar y devuelver todos los libros de la base de datos
        return bookRepository.findAll();
    }

    //findById
//    @GetMapping("/api/books/{id}")
//    public Book findOneById(Long id){
//        return bookRepository.getById(id);
//    }

    //Create

    //Put

    //Delete
}
