package ru.bakhuss.library.service;

import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Сервис для работы с Catalog
 */
public interface CatalogService {

    /**
     * Добавить в каталог запись о книге
     * @param view
     * @return success value
     */
    ResponseView addCatalog(CatalogView view);

    /**
     * Обновить в каталоге запись о книге
     * @param view
     * @return success value
     */
    ResponseView updateCatalog(CatalogView view);

    /**
     * Удалить из каталога запись о книге
     * @param catalogView
     * @return success value
     */
    ResponseView deleteCatalog(CatalogView catalogView);

    /**
     * Получить из каталога одну запись по id
     * @param id
     * @return JSON catalog value
     */
    ResponseView getCatalogById(String id);

    /**
     * Получить все записи из каталога
     * @return JSON catalogs values
     */
    ResponseView getAllCatalogs(CatalogView view);
}
