package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

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
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent")); //nos dice quien envia la peticion. Ej: Firefox con Linux

        if(book.getId() !=null){ //si el id existe, entonces no se puede crear
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }

        //guarda el libro recibido por parametro en la base de datos
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);


    }

    //Put
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId()==null){ //si no tiene id no existe en la base de datos
            log.warn("trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        //guarda el libro actualizado
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //Delete
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(!bookRepository.existsById(id)){
            log.warn("trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //Dellete all
    @DeleteMapping("/api/books")
    public  ResponseEntity<Book> deleteAll(){
        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

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