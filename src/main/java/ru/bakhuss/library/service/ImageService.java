package ru.bakhuss.library.service;

import ru.bakhuss.library.view.ImageView;

/**
 * Сервис для работы с Image
 */
public interface ImageService {

    /**
     * Добавить Image
     * @param view
     * @return id of the new Image
     */
    Long addImage(ImageView view);

    /**
     * Удалить Image
     * @param view
     */
    void deleteImage(ImageView view);

    /**
     * Получить Image по id
     * @param id
     * @return ImageView value
     */
    ImageView getImageById(String id);
}
