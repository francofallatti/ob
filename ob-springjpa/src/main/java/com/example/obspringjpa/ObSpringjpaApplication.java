package com.example.obspringjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringjpaApplication {
	//puento de entrada a nuestra aplicacion
	//Metodo run-> devuelve un aplication context, un contenedor de beans
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObSpringjpaApplication.class, args);
		//Obtengo acceso al contexto con esto ApplicationContext context =
		//Genera un objeto de la implementacion e implementa la interfaz
		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println("find");
		System.out.println("El num de coches en base de datos es: " +  repository.count());

		//podria crear un coche y recuperarlo por id
		Coche toyota = new Coche(null, "Toyota", "Corolla", 2019);
		repository.save(toyota);

		Coche fiat = new Coche(null, "Fiat", "Punto", 2008);
		repository.save(fiat);

		System.out.println("El num de coches en base de datos es: " + repository.count());

		// recuperar todos
		System.out.println(repository.findAll());
	}

}
