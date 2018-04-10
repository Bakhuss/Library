package ru.bakhuss.library.service;

import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Сервис для работы с Book
 */
public interface BookService {

    /**
     * Добавить Book
     * @param view
     * @return success value
     */
    ResponseView addBook(BookView view);

    /**
     * Изменить Book
     * @param view
     * @return success value
     */
    ResponseView updateBook(BookView view);

    /**
     * Удалить Book
     * @param view
     * @return success value
     */
    ResponseView deleteBook(BookView view);

    /**
     * Получить Book по id
     * @param id
     * @return JSON book value
     */
    ResponseView getBookById(Long id);

    /**
     * Получить все Book
     * @return JSON books values
     */
    ResponseView getAllBooks();
}
