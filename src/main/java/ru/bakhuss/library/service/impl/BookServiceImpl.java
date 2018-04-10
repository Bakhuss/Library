package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.BookService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addBook(BookView view) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updateBook(BookView view) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView deleteBook(BookView view) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getBookById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllBooks() {
        return new ResponseView(new Object[1]);
    }
}
