package ru.bakhuss.library.core.book.service;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.catalog.model.Catalog;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;

public interface BookService {
    Long addBook(Book book);

    Book getBook(Long id);

    boolean updateBook(Book book);

    boolean deleteBook(Long id);

    boolean addWriter(Long bookId, Long personId);

    boolean removeWriter(Long bookId, Long personId);

    boolean addCatalog(Long bookId, Long catalogId);

    boolean removeCatalog(Long bookId, Long catalogId);

    List<Person> getAllWriters(Long bookId);

    List<Catalog> getAllCatalogs(Long bookId);
}
