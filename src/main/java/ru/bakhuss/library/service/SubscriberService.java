package ru.bakhuss.library.service;

import ru.bakhuss.library.view.ResponseView;
import ru.bakhuss.library.view.SubscriberView;

/**
 * Сервис для работы с Subscriber
 */
public interface SubscriberService {

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
     * Получить Subscribers по фильтру
     * @return JSON subscribers values
     */
    ResponseView getAllSubscribers(SubscriberView view);

}