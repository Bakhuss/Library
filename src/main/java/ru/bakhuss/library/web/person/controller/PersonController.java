package ru.bakhuss.library.web.person.controller;

import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.web.person.view.PersonView;

public interface PersonController {
    ResponseView addPerson(PersonView view);

    ResponseView getPerson(Person person);

    ResponseView updatePerson(PersonView view);

    ResponseView deletePerson(PersonView view);
}
