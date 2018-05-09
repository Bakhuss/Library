package ru.bakhuss.library.service;

import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.FilterView;

import java.util.Collection;

/**
 * Сервис для работы с Catalog
 */
public interface CatalogService {

    /**
     * Добавить в Catalog запись о книге
     * @param view
     * @return id of the new Catalog
     */
    Long addCatalog(CatalogView view);

    /**
     * Обновить в Catalog запись о книге
     * @param view
     */
    void updateCatalog(CatalogView view);

    /**
     * Удалить из Catalog запись о книге
     * @param view
     */
    void deleteCatalog(CatalogView view);

    /**
     * Получить из Catalog одну запись по id
     * @param id
     * @return CatalogView value
     */
    CatalogView getCatalogById(String id);

    /**
     * Получить все записи из Catalog по фильтру
     * @param view
     * @return CatalogViews values
     */
    Collection<CatalogView> getAllCatalogs(FilterView view);

    /**
     * Узнать общее количество Catalogs
     * @return FilterView#count value
     */
    FilterView getCatalogsCount();
}
