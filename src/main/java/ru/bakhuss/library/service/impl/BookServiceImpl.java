package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.BookService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.PersonView;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class BookServiceImpl implements BookService {
    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addBook(BookView view) {
        if (view.name.isEmpty()) throw new ResponseErrorException("Name is required parameter");
        Book tmpBook = new Book();
        tmpBook.setName(view.name);
        Book newBook = null;
        try {
            newBook = bookDao.save(tmpBook);
            newBook.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving book");
        }
        try {
            List<Long> ids = null;
            ids = view.writers.stream()
                    .map(v -> Long.parseLong(v.id))
                    .collect(Collectors.toList());
            Set<Person> persons = personDao.findByIdIn(ids);
            newBook.setWriters(persons);
        } catch (Exception ex) {
            log.info("Error save writers for book by id: " + newBook.getId() + "\n" + ex.getMessage());
        }
        log.info(newBook.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateBook(BookView view) {
        Book book = null;
        try {
            book = bookDao.findOne(Long.parseLong(view.id));
            /*
             * Проверка на NPE
             */
            book.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting book by id: " + view.id + " from db");
        }
        book.setName(view.name);

        /*
         * Синхронизация писателей книги
         */
        List<Long> idsFrmCtr = view.writers.stream()
                .map(v -> Long.parseLong(v.id))
                .collect(Collectors.toList());
        List<Long> idsFrmDb = book.getWriters().stream()
                .map(Person::getId)
                .collect(Collectors.toList());
        idsFrmDb.removeAll(idsFrmCtr);
        Set<Person> removePrs = personDao.findByIdIn(idsFrmDb);
        Set<Person> addPrs = personDao.findByIdIn(idsFrmCtr);
        book.removeWriters(removePrs);
        book.addWriters(addPrs);

        Book updateBook = null;
        try {
            updateBook = bookDao.save(book);
            updateBook.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error updating book by id: " + view.id);
        }
        log.info(updateBook.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteBook(BookView view) {
        try {
            Long id = Long.parseLong(view.id);
            /*
             * Проверка на NPE
             */
            bookDao.findOne(id).getId();
            bookDao.delete(id);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error removing book by id: " + view.id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public BookView getBookById(String id) {
        Book book = null;
        try {
            book = bookDao.findOne(Long.parseLong(id));
            /*
             * Проверка на NPE
             */
            book.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting book");
        }
        BookView bookV = new BookView();
        bookV.id = book.getId().toString();
        bookV.name = book.getName();
        bookV.writers = book.getWriters().stream()
                .map(PersonView.getFuncPersonToView())
                .sorted(Comparator.comparing(PersonView::getSurname))
                .collect(Collectors.toList());
        log.info(bookV.toString());
        return bookV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<BookView> getAllBooks(BookView view) {
        List<BookView> bookV = null;
        try {
            bookV = StreamSupport.stream(bookDao.findAll().spliterator(), false)
                    .map(BookView.getFuncBookToView())
                    .sorted(Comparator.comparing(BookView::getName))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting persons from db");
        }
        return bookV;
    }
}
