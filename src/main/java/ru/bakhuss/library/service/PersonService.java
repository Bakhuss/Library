package ru.bakhuss.library.service;

import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Сервис для работы с Person
 */
public interface PersonService {

    /**
     * Добавить Person
     * @param view
     * @return success value
     */
    ResponseView addPerson(PersonView view);

    /**
     * Обновить Person
     * @param view
     * @return success value
     */
    ResponseView updatePerson(PersonView view);

    /**
     * Удалить Person
     * @param view
     * @return success value
     */
    ResponseView deletePerson(PersonView view);

    /**
     * Получить Person по id
     * @param id
     * @return JSON person value
     */
    ResponseView getPersonById(String id);

    /**
     * Получить всех Person
     * @return JSON persons values
     */
    ResponseView getAllPersons(PersonView view);
}
