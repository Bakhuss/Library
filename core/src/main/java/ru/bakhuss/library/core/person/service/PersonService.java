package ru.bakhuss.library.core.person.service;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.core.person.model.Person;

import java.util.List;

public interface PersonService {
    Long addPerson(Person person);

    Person getPerson(Long id);

    boolean updatePerson(Person person);

    boolean deletePerson(Long id);

    boolean addWrittenBook(Long personId, Long bookId);

    boolean removeWrittenBook(Long personId, Long bookId);

    List<Book> getAllWrittenBooks(Long personId);
}
