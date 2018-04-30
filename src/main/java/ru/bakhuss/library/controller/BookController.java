package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Контроллер для работы с Book
 */
public interface BookController {

    /**
     * Добавить Book
     * @param view
     * @return success value
     */
    ResponseView addBook(BookView view);

    /**
     * Обновить Book
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
    /**
     * Получить Book по id
     * @param id
     * @param writers  (w)
     * @param catalogs (c)
     * @return JSON book value
     */
    ResponseView getBookById(String id, String writers, String catalogs);

    /**
     * Получить все Book по фильтру
     * @param view
     * @return JSON books values
     */
    ResponseView getAllBooks(FilterView view);

    /**
     * Узнать общее количество книг
     * @return JSON book value
     */
    ResponseView getBooksCount();

}
