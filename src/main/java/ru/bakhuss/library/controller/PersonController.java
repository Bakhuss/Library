package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

public interface PersonController {

    ResponseView addPerson(PersonView view);

    ResponseView updatePerson(PersonView view);

    ResponseView deletePerson(PersonView view);

}
