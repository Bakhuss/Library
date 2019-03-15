package ru.bakhuss.library.book.service;

import ru.bakhuss.library.book.model.Book;

public interface BookService {
    Long addBook(Book book);

    Book getBook(Long id);

    boolean updateBook(Book book);

    boolean deleteBook(Long id);
}
