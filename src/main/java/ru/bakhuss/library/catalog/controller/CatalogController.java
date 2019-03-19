package ru.bakhuss.library.catalog.controller;

import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;

public interface CatalogController {
    ResponseView addCatalog(CatalogView view);

    ResponseView getCatalog(String id);

    ResponseView updateCatalog(CatalogView view);

    ResponseView deleteCatalog(CatalogView view);
}
