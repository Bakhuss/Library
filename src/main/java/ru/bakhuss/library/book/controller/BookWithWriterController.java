package ru.bakhuss.library.book.controller;

import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.person.view.PersonView;

/**
 * Контроллер для действий класса Book с писателем (class Person)
 */
public interface BookWithWriterController {
    ResponseView addWriter(String bookId, PersonView personView);

    ResponseView removeWriter(String bookId, PersonView personView);

    ResponseView getAllWriters(String bookId);
}
