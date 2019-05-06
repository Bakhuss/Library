package ru.bakhuss.library.web.person.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.core.person.service.PersonService;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.web.person.controller.PersonController;
import ru.bakhuss.library.web.person.view.PersonView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.common.Util.parseLongFromString;
import static ru.bakhuss.library.core.person.util.converter.PersonConverterUtil.personToPersonView;
import static ru.bakhuss.library.core.person.util.converter.PersonConverterUtil.personViewToPerson;

@RestController
@RequestMapping(value = "/api/person", produces = APPLICATION_JSON_VALUE)
public class PersonControllerImpl implements PersonController {
    private final Logger log = LoggerFactory.getLogger(PersonControllerImpl.class);

    private final PersonService personService;

    @Autowired
    public PersonControllerImpl(PersonService personService) {
        this.personService = personService;
    }


    @Override
    @PostMapping(value = "/save")
    public ResponseView addPerson(@RequestBody PersonView view) {
        personService.addPerson(personViewToPerson(view));
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getPerson(@PathVariable("id") Person person) {
        if (person == null) throw new ResponseErrorException("Person not found");
        PersonView view = personToPersonView(person);
        view.setId(person.getId().toString());
        System.out.println(person);
        return new ResponseView(view);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updatePerson(@RequestBody PersonView view) {
        Long id = parseLongFromString(view.getId());
        Person person = personService.getPerson(id);
        person.updateState(personViewToPerson(view));
        personService.updatePerson(person);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deletePerson(@RequestBody PersonView view) {
        Long id = parseLongFromString(view.getId());
        personService.deletePerson(id);
        return new ResponseView(true);
    }
}
