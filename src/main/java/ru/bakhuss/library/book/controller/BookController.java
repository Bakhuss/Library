package ru.bakhuss.library.book.controller;

import ru.bakhuss.library.book.view.BookView;
import ru.bakhuss.library.catalog.model.Catalog;
import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.person.view.PersonView;

public interface BookController {
    ResponseView addBook(BookView view);

    ResponseView getBook(String id);

    ResponseView updateBook(BookView view);

    ResponseView deleteBook(BookView view);

    ResponseView addCatalog(String id, CatalogView catalogView);

    ResponseView removeCatalog(String id, CatalogView catalogView);
}
