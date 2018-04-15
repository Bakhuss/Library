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
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.BookService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
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
        List<Long> ids = view.writers.stream()
                .map(v -> Long.parseLong(v.id))
                .collect(Collectors.toList());
        try {
            tmpBook.setWriters(personDao.findByIdIn(ids));
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting writers");
        }
        Book newBook = null;
        try {
            newBook = bookDao.save(tmpBook);
            newBook.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving book");
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

        List<Long> ids = view.writers.stream()
                .map(v -> Long.parseLong(v.id))
                .collect(Collectors.toList());
        Set<Person> persons = null;
        try {
            persons = personDao.findByIdIn(ids);
            System.out.println("persons: " + persons.size());
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting writers");
        }

        log.info("After del: book.getWriters.size: " + String.valueOf(book.getWriters().size()));
        for (Iterator<Person> itr = book.getWriters().iterator(); itr.hasNext();) {
            if (!persons.contains(itr.next())) {
                System.out.println("remove");
                itr.remove();
                System.out.println("remove 2");
            }
        }
        book.getWriters().addAll(persons);
        log.info("After add: book.getWriters.size: " + String.valueOf(book.getWriters().size()));

        Book updateBook = null;
        try {
            updateBook = bookDao.save(book);
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
            bookDao.delete(Long.parseLong(view.id));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + view.id + ")");
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
        Book b = null;
        try {
            b = bookDao.findOne(Long.parseLong(id));

            /*
             * Проверка на NPE
             */
            b.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting book");
        }
        BookView bV = new BookView();
        bV.id = b.getId().toString();
        bV.name = b.getName();
        bV.writers = b.getWriters().stream()
                .map(PersonView.getFuncPersonToView())
                .collect(Collectors.toSet());

        return bV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<BookView> getAllBooks(BookView view) {
        Function<Book, BookView> func = b -> {
            BookView bV = new BookView();
            bV.id = b.getId().toString();
            bV.name = b.getName();
            return bV;
        };

//        ResponseView viewR = new ResponseView();
//        viewR.result = null;
//        try {
//            viewR.data =
//                    StreamSupport.stream(bookDao.findAll().spliterator(), false)
//                            .map(func)
//                            .collect(Collectors.toSet());
//        } catch (Exception ex) {
//            throw new ResponseErrorException("Error getting books");
//        }

        return StreamSupport.stream(bookDao.findAll().spliterator(), false)
                .map(func)
                .collect(Collectors.toSet());
    }
}
