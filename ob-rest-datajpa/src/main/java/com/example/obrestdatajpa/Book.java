package com.example.obrestdatajpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    //atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Double price;
    private LocalDate releapseDate;
    private  Boolean online;

    //constructores
    public Book() {
    }

    public Book(Long id, String title, String author, Integer pages, Double price, LocalDate releapseDate, Boolean online) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.releapseDate = releapseDate;
        this.online = online;
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleapseDate() {
        return releapseDate;
    }

    public void setReleapseDate(LocalDate releapseDate) {
        this.releapseDate = releapseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
