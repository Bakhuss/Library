package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.service.PersonService;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import java.util.HashSet;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addPerson(PersonView view) {
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updatePerson(PersonView view) {
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView deletePerson(PersonView view) {
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getPersonById(Long id) {
        return new ResponseView(new Object());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllPersons() {
        return new ResponseView(new HashSet<>());
    }
}
