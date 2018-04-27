package ru.bakhuss.library.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bakhuss.library.Application;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
public class BookServiceImplTest {

    @Test
    public void addBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void getBookById() {
    }

    @Test
    public void getAllBooks() {
    }
}