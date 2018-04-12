package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Контроллер для работы с Person
 */
public interface PersonController {

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
     * Получить всех Person по фильтру
     * @param view
     * @return JSON persons values
     */
    ResponseView getPersonsByFilter(PersonView view);
}
