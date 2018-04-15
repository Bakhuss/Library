package ru.bakhuss.library.service;

import ru.bakhuss.library.view.CatalogView;

import java.util.Set;

/**
 * Сервис для работы с Catalog
 */
public interface CatalogService {

    /**
     * Добавить в каталог запись о книге
     * @param view
     */
    void addCatalog(CatalogView view);

    /**
     * Обновить в каталоге запись о книге
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
     * Получить все записи из Catalog
     * @return CatalogViews values
     */
    /**
     * Получить все записи из Catalog по фильтру
     * @param view
     * @return CatalogViews values
     */
    Set<CatalogView> getAllCatalogs(CatalogView view);
}
