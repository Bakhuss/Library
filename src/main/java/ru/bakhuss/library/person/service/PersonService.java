package ru.bakhuss.library.person.service;

import ru.bakhuss.library.person.model.Person;

public interface PersonService {
    Long addPerson(Person person);

    Person getPerson(Long id);

    boolean updatePerson(Person person);

    boolean deletePerson(Long id);

    boolean addWrittenBook(Long personId, Long bookId);

    boolean removeWrittenBook(Long personId, Long bookId);
}
