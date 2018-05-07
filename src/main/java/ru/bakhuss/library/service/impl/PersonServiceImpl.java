package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.PersonService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.PersonView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Long addPerson(PersonView view) {
        if (view.surname.isEmpty()) throw new ResponseErrorException("Surname is required parameter");
        Person tmpPrs = new Person();
        tmpPrs.setFirstName(view.firstName);
        tmpPrs.setSecondName(view.secondName);
        tmpPrs.setSurname(view.surname);
        tmpPrs.setBirthday(view.birthday);
        tmpPrs.setPhone(view.phone);
        tmpPrs.setEmail(view.email);
        Person newPrs = null;
        try {
            newPrs = personDao.save(tmpPrs);
            newPrs.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving new person");
        }
        log.info(newPrs.toString());
        return newPrs.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updatePerson(PersonView view) {
        Person person = null;
        try {
            person = personDao.findOne(Long.parseLong(view.id));
            /*
             * Проверка на NPE;
             */
            person.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Person id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting person by id: " + view.id + " from db");
        }

        person.setFirstName(view.firstName);
        person.setSecondName(view.secondName);
        person.setSurname(view.surname);
        person.setBirthday(view.birthday);

        /*
         * Синхронизация написанных книг
         */
        if (view.writtenBooks != null) {
            List<Long> idsFrmCtr = view.writtenBooks.stream()
                    .map(v -> Long.parseLong(v.id))
                    .collect(Collectors.toList());
            List<Long> idsFrmDb = person.getWrittenBooks().stream()
                    .map(Book::getId)
                    .collect(Collectors.toList());
            for (Long l : idsFrmCtr) {
                Book book = bookDao.findOne(l);
                if (idsFrmDb.contains(l)) person.removeWrittenBook(book);
                else person.addWrittenBook(book);
            }
        }

        Person updatePrs = null;
        try {
            updatePrs = personDao.save(person);
            updatePrs.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error updating person by id:" + view.id);
        }
        log.info(updatePrs.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deletePerson(PersonView view) {
        Person delPrs = null;
        try {
            Long id = Long.parseLong(view.id);
            /*
             * Проверка на NPE
             */
            delPrs = personDao.findOne(id);
            Set<Book> delBooks = new HashSet<>(delPrs.getWrittenBooks());
            for (Book b : delBooks) {
                delPrs.removeWrittenBook(b);
            }
            personDao.delete(delPrs.getId());
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error removing person by id: " + view.id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public PersonView getPersonById(String id) {
        Person person = null;
        try {
            person = personDao.findOne(Long.parseLong(id));

            /*
             *Проверка на NPE
             */
            person.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Person id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting person");
        }

        PersonView personV = new PersonView();
        personV.id = person.getId().toString();
        personV.firstName = person.getFirstName();
        personV.secondName = person.getSecondName();
        personV.surname = person.getSurname();
        personV.birthday = person.getBirthday();
        personV.writtenBooks = person.getWrittenBooks().stream()
                .map(BookView.getFuncBookToView())
                .sorted(Comparator.comparing(BookView::getName))
                .collect(Collectors.toList());
        log.info(personV.toString());
        return personV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<PersonView> getAllPersons(FilterView view) {
        List<PersonView> personsV = null;
        int page = Integer.parseInt(view.page);
        int fetchSize = Integer.parseInt(view.fetchSize);
        String props = view.orderSort;
        System.out.println("orderSort: " + props);
        Sort.Direction direct = null;
        switch (view.orderSort) {
            case ("asc"):
                direct = Sort.Direction.ASC;
                break;
            case ("desc"):
                direct = Sort.Direction.DESC;
                break;
            default:
                direct = Sort.Direction.ASC;
        }
        Sort sort = new Sort(direct, props);
        Pageable pageable = new PageRequest(page, fetchSize, sort);
        try {
            Page<Person> personPage = personDao.findAll(pageable);
            List<Person> listPerson = personPage.getContent();
            personsV = listPerson.stream()
                    .map(PersonView.getFuncPersonToView())
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new ResponseErrorException("Error requesting persons from db");
        }
        log.info(personsV.toString());
        return personsV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public FilterView getPersonsCount() {
        Long count;
        try {
            count = personDao.count();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting persons count");
        }
        FilterView filterV = new FilterView();
        filterV.count = String.valueOf(count);
        log.info("count: " + filterV.count);
        return filterV;
    }
}
