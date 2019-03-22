package ru.bakhuss.library.person.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.book.view.BookView;
import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.person.controller.PersonWithWrittenBookController;
import ru.bakhuss.library.person.service.PersonService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/person/{id}", produces = APPLICATION_JSON_VALUE)
public class PersonWithWrittenBookControllerImpl implements PersonWithWrittenBookController {
    private final Logger log = LoggerFactory.getLogger(PersonWithWrittenBookControllerImpl.class);

    private final PersonService personService;

    @Autowired
    public PersonWithWrittenBookControllerImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping(value = "/add-written-book")
    public ResponseView addWrittenBook(@PathVariable("id") String personId,
                                       @RequestBody BookView bookView) {
        Long longPersonId = parseLongFromString(personId);
        Long bookId = parseLongFromString(bookView.getId());
        personService.addWrittenBook(longPersonId, bookId);
        log.info("personId: " + personId);
        log.info(bookView.toString());
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/remove-written-book")
    public ResponseView removeWrittenBook(@PathVariable("id") String personId,
                                          @RequestBody BookView bookView) {
        Long longPersonId = parseLongFromString(personId);
        Long bookId = parseLongFromString(bookView.getId());
        log.info("personId: " + personId);
        log.info(bookView.toString());
        personService.removeWrittenBook(longPersonId, bookId);
        return new ResponseView(true);
    }
}
