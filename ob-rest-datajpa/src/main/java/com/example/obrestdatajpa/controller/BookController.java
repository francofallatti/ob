package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

//        opcion1:
        if(bookOptional.isPresent()){
            return ResponseEntity.ok(bookOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
//        opcion2;
//        return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent")); //nos dice quien envia la peticion. Ej: Firefox con Linux
        //guarda el libro recibido por parametro en la base de datos
        return bookRepository.save(book);
    }
    //Put

    //Delete
}
//    sin responseEntity
//    public Book findOneById(@PathVariable Long id){
//        Optional<Book> bookOptional = bookRepository.findById(id);
//
//      opcion 1:
//        if(bookOptional.isPresent()){
//            return bookOptional.get();
//        }else{
//            return null;
//        }
//      opcion2:
//        return bookOptional.orElse(null);
//    }