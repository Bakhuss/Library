package ru.bakhuss.library.service;

import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.PersonView;

import java.util.Collection;

/**
 * Сервис для работы с Person
 */
public interface PersonService {

    /**
     * Добавить Person
     * @param view
     * @return id of the new Person
     */
    Long addPerson(PersonView view);

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
    PersonView getPersonById(String id,
                             String books,
                             String catalogs);

    /**
     * Получить все Person по фильтру
     * @param view
     * @return PersonViews values
     */
    Collection<PersonView> getAllPersons(FilterView view);

    /**
     * Узнать общее количество человек
     * @return FilterView#count value
     */
    FilterView getPersonsCount();
}
