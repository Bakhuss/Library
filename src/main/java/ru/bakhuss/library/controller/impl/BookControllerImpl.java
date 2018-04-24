package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.BookController;
import ru.bakhuss.library.service.BookService;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/book", produces = APPLICATION_JSON_VALUE)
public class BookControllerImpl implements BookController {
    private final BookService bookService;

    @Autowired
    private BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addBook(@RequestBody BookView view) {
        bookService.addBook(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/update")
    public ResponseView updateBook(@RequestBody BookView view) {
        bookService.updateBook(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deleteBook(@RequestBody BookView view) {
        bookService.deleteBook(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getBookById(@PathVariable String id,
                                    @RequestParam(name = "w", required = false) String writers,
                                    @RequestParam(name = "c", required = false) String catalogs) {
        return new ResponseView(bookService.getBookById(id, writers, catalogs));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/list")
    public ResponseView getAllBooks(@RequestBody BookView view) {
        return new ResponseView(bookService.getAllBooks(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/count")
    public ResponseView getBooksCount() {
        return new ResponseView(bookService.getBooksCount());
    }
}
