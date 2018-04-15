package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.dao.SubscriberCatalogDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.PersonService;
import ru.bakhuss.library.service.SubscriberService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonDao personDao;
    private final SubscriberDao subscriberDao;
    private final SubscriberCatalogDao subscriberCatalogDao;
    private final SubscriberService subscriberService;

    @Autowired
    public PersonServiceImpl(PersonDao personDao,
                             SubscriberDao subscriberDao,
                             SubscriberCatalogDao subscriberCatalogDao,
                             SubscriberService subscriberService) {
        this.personDao = personDao;
        this.subscriberDao = subscriberDao;
        this.subscriberCatalogDao = subscriberCatalogDao;
        this.subscriberService = subscriberService;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addPerson(PersonView view) {
        Person p = new Person();
        p.setFirstName(view.firstName);
        p.setSecondName(view.secondName);
        p.setSurname(view.surname);
        p.setBirthday(view.birthday);
        Person newP = null;
        try {
            newP = personDao.save(p);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving new person");
        }
        log.info(newP.toString());

        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updatePerson(PersonView view) {
        Person p = null;
        try {
            p = personDao.findOne(Long.parseLong(view.id));

            /*
             * Проверка на NPE;
             */
            p.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Person id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting person");
        }

        p.setFirstName(view.firstName);
        p.setSecondName(view.secondName);
        p.setSurname(view.surname);
        p.setBirthday(view.birthday);
        try {
            personDao.save(p);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error updating person by id:" + view.id);
        }

        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView deletePerson(PersonView view) {
        try {
            personDao.delete(Long.parseLong(view.id));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("id must be a number(" + view.id + ")");
        } catch (Exception ex) {
            throw new ResponseErrorException("Error deleting person by id: " + view.id);
        }
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getPersonById(String id) {
        Person p = null;
        try {
            p = personDao.findOne(Long.parseLong(id));

            /*
             *Проверка на NPE
             */
            p.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Person id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting person");
        }

        PersonView pV = new PersonView();
        pV.id = p.getId().toString();
        pV.firstName = p.getFirstName();
        pV.secondName = p.getSecondName();
        pV.surname = p.getSurname();
        pV.birthday = p.getBirthday();

        pV.writtenBooks = p.getBooks().stream()
                .map(BookView.getFuncBookToView())
                .collect(Collectors.toSet());

        log.info(pV.toString());

        return new ResponseView(pV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllPersons(PersonView view) {
        ResponseView respP = new ResponseView();
        respP.result = null;
        respP.data =
                StreamSupport.stream(personDao.findAll().spliterator(), false)
                        .map(PersonView.getFuncPersonToView())
                        .collect(Collectors.toSet());
        return respP;
    }
}
