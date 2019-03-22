package ru.bakhuss.library.person.controller;

import ru.bakhuss.library.book.view.BookView;
import ru.bakhuss.library.common.view.ResponseView;

/**
 * Контроллер для действий класса Person с написанной книгой (class Book)
 */
public interface PersonWithWrittenBookController {
    ResponseView addWrittenBook(String personId, BookView bookView);

    ResponseView removeWrittenBook(String personId, BookView bookView);

    ResponseView getAllWrittenBooks(String personId);
}
