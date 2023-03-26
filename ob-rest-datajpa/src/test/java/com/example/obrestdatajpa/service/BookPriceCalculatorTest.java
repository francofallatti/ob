package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {


    @Test
    void calculatePrice() {
        Book book = new Book(1L,"El señor de los anillos","J. R. R. Tolkien", 1589,49.01, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        assertEquals(57.00,calculator.calculatePrice(book));
    }
}