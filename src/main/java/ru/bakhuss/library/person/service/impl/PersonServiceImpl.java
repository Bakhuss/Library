package ru.bakhuss.library.person.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.book.dao.BookDao;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.person.dao.PersonDao;
import ru.bakhuss.library.person.model.Person;
import ru.bakhuss.library.person.service.PersonService;

import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao,
                             BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public Long addPerson(Person person) {
        Person newPerson = personDao.save(person);
        log.info(newPerson.toString());
        return newPerson.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        Person person = personDao.findById(id)
                .orElseThrow(() -> new ResponseErrorException(
                        ("Person by id = " + id + " not found")
                ));
        log.info(person.toString());
        return person;
    }

    @Override
    @Transactional
    public boolean updatePerson(Person person) {
        Person updatedPerson = personDao.save(person);
        log.info(updatedPerson.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean deletePerson(Long id) {
        if (!personDao.existsById(id))
            throw new ResponseErrorException("Not found person by id " + id);
        else {
            personDao.deleteById(id);
            log.info("Deleted person by id " + id);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean addWrittenBook(Long personId, Long bookId) {
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new ResponseErrorException(
                        "Not found person by id " + personId
                ));

        boolean hasBook = person.getWrittenBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList())
                .contains(bookId);

        if (!hasBook) {
            Book book = bookDao.findById(bookId)
                    .orElseThrow(() -> new ResponseErrorException(
                            "Not found book by id " + bookId
                    ));
            person.addBook(book);
        } else log.warn("Person by id " + personId + " has written book by id " + bookId);
        return true;
    }

    @Override
    @Transactional
    public boolean removeWrittenBook(Long personId, Long bookId) {
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new ResponseErrorException(
                        "Not found person by id " + personId
                ));
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new ResponseErrorException(
                        "Not found book by id " + bookId
                ));
        person.removeBook(book);
        return true;
    }
}
