package ru.bakhuss.library.service;

import ru.bakhuss.library.view.LibraryCardView;

import java.util.Collection;

/**
 * Сервис для работы с LibraryCard
 */
public interface LibraryCardService {

    /**
     * Добавить LibraryCard
     * @param view
     */
    void addLibraryCard(LibraryCardView view);

    /**
     * Обновить LibraryCard
     * @param view
     */
    void updateLibraryCard(LibraryCardView view);

    /**
     * Получить LibraryCard по subscriberId
     * @param id
     * @return LibraryCardView value
     */
    LibraryCardView getBySubscriberId(String id);

    /**
     * Получить LibraryCard по catalogId
     * @param id
     * @return LibraryCardView value
     */
    LibraryCardView getByCatalogId(String id);

    /**
     * Получить все LibraryCard по фильтру
     * @param view
     * @return LibraryCardViews values
     */
    Collection<LibraryCardView> getAll(LibraryCardView view);

}
