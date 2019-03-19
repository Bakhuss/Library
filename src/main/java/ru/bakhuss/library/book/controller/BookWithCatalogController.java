package ru.bakhuss.library.book.controller;

import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;

/**
 * Действия класса Book с классом Catalog
 */
public interface BookWithCatalogController {
    ResponseView addCatalog(String id, CatalogView catalogView);

    ResponseView removeCatalog(String id, CatalogView catalogView);

    ResponseView getAllCatalogs(String bookId);
}
