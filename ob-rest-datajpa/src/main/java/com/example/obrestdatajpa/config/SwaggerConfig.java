package com.example.obrestdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


//configuracion Swagger para la generacion de documentacion de la API Rest
//HTML: http://localhost:8088/swagger-ui/
//JSON: http://localhost:8088/v2/api-docs

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        System.out.println("swagger");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Spring Boot Book API REST",
                "Library Api rest docs",
                "1.0",
                "https://github.com/francofallatti/ob",
                new Contact(
                        "Franco Fallatti",
                        "https://www.linkedin.com/in/franco-fallatti/",
                        "francofallatti@gmail.com"),
                "MIT",
                "https://github.com/francofallatti/ob",
                Collections.emptyList());
    }
}
