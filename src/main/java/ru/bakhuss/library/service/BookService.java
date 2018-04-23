package ru.bakhuss.library.service;

import ru.bakhuss.library.view.BookView;

import java.util.Collection;

/**
 * Сервис для работы с Book
 */
public interface BookService {

    /**
     * Добавить Book
     * @param view
     */
    void addBook(BookView view);

    /**
     * Изменить Book
     * @param view
     */
    void updateBook(BookView view);

    /**
     * Удалить Book
     * @param view
     */
    void deleteBook(BookView view);

    /**
     * Получить Book по id
     * @param id
     * @return BookView value
     */
    BookView getBookById(String id,
                         String writers,
                         String catalogs);

    /**
     * Получить все Book по фильтру
     * @param view
     * @return BookViews values
     */
    Collection<BookView> getAllBooks(BookView view);
}
