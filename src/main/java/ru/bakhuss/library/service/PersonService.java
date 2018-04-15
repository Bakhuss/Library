package ru.bakhuss.library.service;

import ru.bakhuss.library.view.PersonView;

import java.util.Collection;

/**
 * Сервис для работы с Person
 */
public interface PersonService {

    /**
     * Добавить Person
     * @param view
     */
    void addPerson(PersonView view);

    /**
     * Обновить Person
     * @param view
     */
    void updatePerson(PersonView view);

    /**
     * Удалить Person
     * @param view
     */
    void deletePerson(PersonView view);

    /**
     * Получить Person по id
     * @param id
     * @return PersonView value
     */
    PersonView getPersonById(String id);

    /**
     * Получить всех Person по фильтру
     * @param view
     * @return PersonViews values
     */
    Collection<PersonView> getAllPersons(PersonView view);
}
