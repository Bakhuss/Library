package ru.bakhuss.library.web.person.controller;

import ru.bakhuss.library.web.person.view.PersonView;
import ru.bakhuss.library.common.view.ResponseView;

public interface PersonController {
    ResponseView addPerson(PersonView view);

    ResponseView getPerson(String id);

    ResponseView updatePerson(PersonView view);

    ResponseView deletePerson(PersonView view);
}
