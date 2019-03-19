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
import ru.bakhuss.library.book.controller.BookWithCatalogController;
import ru.bakhuss.library.book.dao.BookDao;
import ru.bakhuss.library.book.service.BookService;
import ru.bakhuss.library.catalog.dao.CatalogDao;
import ru.bakhuss.library.catalog.model.Catalog;
import ru.bakhuss.library.catalog.service.CatalogService;
import ru.bakhuss.library.catalog.util.converter.CatalogConverterUtil;
import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.model.Person;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.common.Util.parseLongFromString;

@RestController
@RequestMapping(value = "/api/book/{id}", produces = APPLICATION_JSON_VALUE)
public class BookWithCatalogControllerImpl implements BookWithCatalogController {
    private final Logger log = LoggerFactory.getLogger(BookWithCatalogControllerImpl.class);

    private final BookService bookService;

    @Autowired
    public BookWithCatalogControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @PostMapping(value = "/add-catalog")
    public ResponseView addCatalog(@PathVariable String id,
                                   @RequestBody CatalogView catalogView) {
        Long bookId = parseLongFromString(id);
        Long catalogId = parseLongFromString(catalogView.getId());
        bookService.addCatalog(bookId, catalogId);
        return new ResponseView(true);
    }

    @Override
    @DeleteMapping(value = "/remove-catalog")
    public ResponseView removeCatalog(@PathVariable String id,
                                      @RequestBody CatalogView catalogView) {
        Long bookId = parseLongFromString(id);
        Long catalogId = parseLongFromString(catalogView.getId());
        bookService.removeCatalog(bookId, catalogId);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/all-catalogs")
    public ResponseView getAllCatalogs(@PathVariable("id") String bookId) {
        Long id = parseLongFromString(bookId);
        List<Catalog> allCatalogs = bookService.getAllCatalogs(id);
        List<CatalogView> catalogs = allCatalogs.stream()
                .map(CatalogConverterUtil::catalogToCatalogView)
                .collect(Collectors.toList());
        log.info(catalogs.toString());
        return new ResponseView(catalogs);
    }
}
