package ru.bakhuss.library.book.controller;

import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.person.view.PersonView;

/**
 * Действия класса Book с писателем
 */
public interface BookWithWriterController {
    ResponseView addWriter(String id, PersonView personView);

    ResponseView removeWriter(String id, PersonView personView);

    ResponseView getAllWriters(String bookId);
}
