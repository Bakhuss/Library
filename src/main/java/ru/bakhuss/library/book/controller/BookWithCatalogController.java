package ru.bakhuss.library.book.controller;

import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;

/**
 * Контроллер для действий класса Book с классом Catalog
 */
public interface BookWithCatalogController {
    ResponseView addCatalog(String bookId, CatalogView catalogView);

    ResponseView removeCatalog(String bookId, CatalogView catalogView);

    ResponseView getAllCatalogs(String bookId);
}
