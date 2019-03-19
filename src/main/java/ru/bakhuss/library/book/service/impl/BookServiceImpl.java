package ru.bakhuss.library.book.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.book.dao.BookDao;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.book.service.BookService;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.person.dao.PersonDao;
import ru.bakhuss.library.person.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao,
                           PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public Long addBook(Book book) {
        Book newBook = bookDao.save(book);
        log.info(newBook.toString());
        return newBook.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(() -> new ResponseErrorException(
                        ("Book by id = " + id + " not found")
                ));
        log.info(book.toString());
        System.out.println("1 ----------------");
        for (Person p : book.getWriters()) {
            System.out.println(p);
        }
        System.out.println("2 ----------------");
        return book;
    }

    @Override
    @Transactional
    public boolean updateBook(Book book) {
        Book updatedBook = bookDao.save(book);
        log.info(updatedBook.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBook(Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(() -> new ResponseErrorException(
                        "Not found book by id " + id
                ));
        book.setWriters(null);
        bookDao.deleteById(id);
        log.info("Deleted book by id " + id);
        return true;
    }

    @Override
    @Transactional
    public boolean addWriter(Long bookId, Long personId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new ResponseErrorException(
                        "Not found book by id " + bookId
                ));

        boolean hasWriter = book.getWriters().stream()
                .map(Person::getId)
                .collect(Collectors.toList())
                .contains(personId);

        if (!hasWriter) {
            Person person = personDao.findById(personId)
                    .orElseThrow(() -> new ResponseErrorException(
                            "Not found person by id " + personId
                    ));
            book.addWriter(person);
        } else log.warn("Book by id " + bookId + " has writer by person id " + personId);
        return true;
    }
}
