package ru.bakhuss.library.book.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.Application;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.book.service.BookService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext
@SpringBootTest(classes = {Application.class})
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addBook_shouldSaveNewObjectToRepository() {
        Book book = new Book();
        book.setName("MyBook");
        Long newBookId = bookService.addBook(book);
        Book bookFromDb = bookService.getBook(newBookId);
        Assert.assertSame(book,bookFromDb);
    }

    @Test
    public void getBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void deleteBook() {
    }
}
