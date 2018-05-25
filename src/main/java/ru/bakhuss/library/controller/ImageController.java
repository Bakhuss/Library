package ru.bakhuss.library.controller;

import ru.bakhuss.library.view.ImageView;
import ru.bakhuss.library.view.ResponseView;

/**
 * Контроллер для работы с Image
 */
public interface ImageController {

    /**
     * Добавить Image
     * @param view
     * @return success value
     */
    ResponseView addImage(ImageView view);

    /**
     * Удалить Image
     * @param view
     * @return success value
     */
    ResponseView deleteImage(ImageView view);

    /**
     * Получить Image по id
     * @param id
     * @return JSON image value
     */
    ResponseView getImageById(String id);
}
