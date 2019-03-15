package ru.bakhuss.library.controller;

import ru.bakhuss.library.common.view.ResponseView;
import ru.bakhuss.library.view.SubscriberView;

/**
 * Контроллер для работы с Subscriber
 */
public interface SubscriberController {

    /**
     * Добавить Subscriber
     * @param view
     * @return success value
     */
    ResponseView addSubscriber(SubscriberView view);

    /**
     * Обновить Subscriber
     * @param view
     * @return success value
     */
    ResponseView updateSubscriber(SubscriberView view);

    /**
     * Удалить Subscriber
     * @param view
     * @return success value
     */
    ResponseView deleteSubscriber(SubscriberView view);

    /**
     * Получить Subscriber по id
     * @param id
     * @return JSON subscriber value
     */
    ResponseView getSubscriberById(String id);

    /**
     * Получить всех Subscribers по фильтру
     * @return JSON subscribers values
     */
    ResponseView getSubscribers(SubscriberView view);

}
