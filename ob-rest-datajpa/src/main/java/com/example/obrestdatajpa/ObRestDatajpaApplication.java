package com.example.obrestdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//crear libro
		Book book1 = new Book(null, "Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2018,02,1),true);
		Book book2 = new Book(null, "Homo Sapiens", "Yuval Noah", 350, 19.99, LocalDate.of(2013,06,1),true);
		System.out.println("Cantidad de libros de base de datos: " + repository.findAll().size());
		//almacenar libro
		repository.save(book1);
		repository.save(book2);
		//recuperar todos
		System.out.println("Cantidad de libros de base de datos: " + repository.findAll().size());
		//borrar libro
		repository.deleteById(1L);
		System.out.println("Cantidad de libros de base de datos: " +


				repository.findAll().size());

	}

}
