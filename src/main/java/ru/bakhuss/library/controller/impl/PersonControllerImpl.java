package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.PersonController;
import ru.bakhuss.library.service.PersonService;
import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/person", produces = APPLICATION_JSON_VALUE)
public class PersonControllerImpl implements PersonController {
    private final PersonService personService;

    @Autowired
    private PersonControllerImpl(PersonService personService) {
        this.personService = personService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addPerson(@RequestBody PersonView view) {
        personService.addPerson(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/update")
    public ResponseView updatePerson(@RequestBody PersonView view) {
        System.out.println("---------- view: " + view.toString());
        personService.updatePerson(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deletePerson(@RequestBody PersonView view) {
        personService.deletePerson(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getPersonById(@PathVariable String id,
                                      @RequestParam(name = "b", required = false) String books,
                                      @RequestParam(name = "c", required = false) String catalogs) {
        return new ResponseView(personService.getPersonById(id, books, catalogs));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/list")
    public ResponseView getPersonsByFilter(@RequestBody FilterView view) {
        return new ResponseView(personService.getAllPersons(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/count")
    public ResponseView getPersonsCount() {
        return new ResponseView(personService.getPersonsCount());
    }
}
