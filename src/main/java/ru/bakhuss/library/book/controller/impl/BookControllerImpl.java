package ru.bakhuss.library.book.controller.impl;

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
import ru.bakhuss.library.book.controller.BookController;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.book.service.BookService;
import ru.bakhuss.library.book.view.BookView;
import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.person.service.PersonService;
import ru.bakhuss.library.person.view.PersonView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/book", produces = APPLICATION_JSON_VALUE)
public class BookControllerImpl implements BookController {
    private final Logger log = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookControllerImpl(BookService bookService,
                              PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @Override
    @PostMapping(value = "/save")
    public ResponseView addBook(@RequestBody BookView view) {
        log.info(view.toString());
        Book book = new Book();
        book.setName(view.getName());
        bookService.addBook(book);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getBook(@PathVariable String id) {
        Long longId = parseLongFromString(id);
        Book book = bookService.getBook(longId);
        BookView view = new BookView();
        view.setId(book.getId().toString());
        view.setName(book.getName());
        log.info(view.toString());
        return new ResponseView(view);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updateBook(@RequestBody BookView view) {
        log.info(view.toString());
        Long id = parseLongFromString(view.getId());
        Book book = bookService.getBook(id);
        book.setName(view.getName());
        bookService.updateBook(book);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deleteBook(@RequestBody BookView view) {
        Long longId = parseLongFromString(view.getId());
        bookService.deleteBook(longId);
        return new ResponseView(true);
    }

    @Override
    @PostMapping(value = "/{id}/add-writer")
    public ResponseView addWriter(@PathVariable String id,
                                  @RequestBody PersonView personView) {
        Long bookId = parseLongFromString(id);
        Long personId = parseLongFromString(personView.getId());
        bookService.addWriter(bookId, personId);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/{id}/remove-writer")
    public ResponseView removeWriter(@PathVariable String id,
                                     @RequestBody PersonView personView) {
        Long bookId = parseLongFromString(id);
        Long personId = parseLongFromString(personView.getId());
        bookService.removeWriter(bookId, personId);
        return new ResponseView(true);
    }

    @Override
    @PostMapping(value = "/{id}/add-catalog")
    public ResponseView addCatalog(@PathVariable String id,
                                   @RequestBody CatalogView catalogView) {
        Long bookId = parseLongFromString(id);
        Long catalogId = parseLongFromString(catalogView.getId());
        bookService.addCatalog(bookId, catalogId);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/{id}/remove-catalog")
    public ResponseView removeCatalog(@PathVariable String id,
                                      @RequestBody CatalogView catalogView) {
        Long bookId = parseLongFromString(id);
        Long catalogId = parseLongFromString(catalogView.getId());
        bookService.removeCatalog(bookId, catalogId);
        return new ResponseView(true);
    }
}
