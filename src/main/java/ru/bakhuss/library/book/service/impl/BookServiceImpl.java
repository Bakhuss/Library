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

@Service
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
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
        return book;
    }

    @Override
    @Transactional
    public boolean updateBook(Book book) {
        Book bookFromDao = bookDao.findById(book.getId())
                .orElseThrow(() -> new ResponseErrorException(
                        ("Book by id = " + book.getId() + " not found")
                ));
        bookFromDao.setName(book.getName());
        Book updatedBook = bookDao.save(bookFromDao);
        log.info(updatedBook.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBook(Long id) {
        bookDao.deleteById(id);
        return true;
    }
}
