package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Manera más sencilla de tester controladores
//webEnvironment -> indicar que le asigne un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    //Sirve para poder hacer las peticiones http
    //Alternativa a restTemplate que sirve para testing
    private TestRestTemplate testRestTemplate;

    //Sirve para construir TestRestTemplate
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    //Le decimos a Spring que inyecte un builder (RestTemplateBuilder) el cual usamos para generar el objeto
    // TestRestTemplate y asi poder lanzar las peticiones http vinculadas a la web con el puerto
    // SpringBootTest.WebEnvironment.RANDOM_PORT

    @LocalServerPort
    private int port;

    @BeforeEach
    void  setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar hola mundo desde controladores Spring REST")
    @Test
    void hello(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola",String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        assertEquals("holaa mundo",response.getBody());
    }

    @Test
    void findAll() {
        //testRestTemplate.getForEntity -> realiza peticion get que devuelve una lista de entidades book
        ResponseEntity<Book[]> responseEntity = testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<Book> books = Arrays.asList(responseEntity.getBody());
        assertEquals(0,books);
    }

    @Test
    void findOneById() {
        ResponseEntity<Book> responseEntity = testRestTemplate.getForEntity("/api/books", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void create() { //preparar cabecera, preparar json, preparar petición y ejecutar petición a traves del metodo POST
        //cabecera para enviar el json
        HttpHeaders headers = new HttpHeaders();
        //indico que voy a enviar un json
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                 {
                        "title": "Harry Potter y la piedra filosofal",
                        "author": "J.K. Rowling",
                        "pages": 320,
                        "price": 39.99,
                        "releapseDate": "1997-06-26",
                        "online": true
                    }
                """;

        //creamos la request (lo mismo que en postman pero con java)
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        //exchange -> se usa para POST y devuelve la entidad libro
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);

        Book result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Harry Potter y la piedra filosofal", result.getTitle());
    }
}