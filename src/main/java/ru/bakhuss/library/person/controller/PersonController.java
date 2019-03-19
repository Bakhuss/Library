package ru.bakhuss.library.person.controller;

import ru.bakhuss.library.book.view.BookView;
import ru.bakhuss.library.person.view.PersonView;
import ru.bakhuss.library.common.view.ResponseView;

public interface PersonController {
    ResponseView addPerson(PersonView view);

    ResponseView getPerson(String id);

    ResponseView updatePerson(PersonView view);

    ResponseView deletePerson(PersonView view);

    ResponseView addWrittenBook(String id, BookView bookView);

    ResponseView removeWrittenBook(String id, BookView bookView);
}
