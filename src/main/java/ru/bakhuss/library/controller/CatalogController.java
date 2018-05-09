package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Контроллер для работы с Catalog
 */
public interface CatalogController {

    /**
     * Добавить Catalog
     * @param view
     * @return success value
     */
    ResponseView addCatalog(CatalogView view);

    /**
     * Обновить Catalog
     * @param view
     * @return success value
     */
    ResponseView updateCatalog(CatalogView view);

    /**
     * Удалить Catalog
     * @param view
     * @return success value
     */
    ResponseView deleteCatalog(CatalogView view);

    /**
     * Получить Catalog по id
     * @param id
     * @return JSON catalog value
     */
    ResponseView getCatalogById(String id);

    /**
     * Получить Catalogs по фильтру
     * @param view
     * @return JSON catalogs values
     */
    ResponseView getCatalogs(FilterView view);

    /**
     * Получить общее количество Catalogs
     * @return JSON filter value
     */
    ResponseView getCatalogsCount();
}
