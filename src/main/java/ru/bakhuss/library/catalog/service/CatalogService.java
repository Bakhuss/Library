package ru.bakhuss.library.catalog.service;

import ru.bakhuss.library.catalog.model.Catalog;

public interface CatalogService {
    Long addCatalog(Catalog catalog);

    Catalog getCatalog(Long id);

    boolean updateCatalog(Catalog catalog);

    boolean deleteCatalog(Long id);
}
