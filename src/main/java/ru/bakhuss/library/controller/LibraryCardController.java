package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.LibraryCardView;
import ru.bakhuss.library.common.view.ResponseView;

/**
 * Контроллер для работы с LibraryCard
 */
public interface LibraryCardController {

    /**
     * Добавить запись в LibraryCard
     * @param view
     * @return success value
     */
    ResponseView addCatalog(LibraryCardView view);

    /**
     * Удалить запись из LibraryCard (установить returnDate)
     * @param view
     * @return success value
     */
    ResponseView deleteCatalog(LibraryCardView view);

    /**
     * Получить LibraryCards по фильтру
     * @param view
     * @return JSON library cards values
     */
    ResponseView getAllLibraryCards(LibraryCardView view);

}
