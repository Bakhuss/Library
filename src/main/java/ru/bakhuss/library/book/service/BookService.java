package ru.bakhuss.library.book.service;

import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.person.model.Person;

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
}
