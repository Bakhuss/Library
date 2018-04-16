package ru.bakhuss.library.service;

import ru.bakhuss.library.view.SubscriberView;

import java.util.Collection;

/**
 * Сервис для работы с Subscriber
 */
public interface SubscriberService {

    /**
     * Добавить Subscriber
     * @param view
     */
    void addSubscriber(SubscriberView view);

    /**
     * Обновить Subscriber
     * @param view
     */
    void updateSubscriber(SubscriberView view);

    /**
     * Удалить Subscriber
     * @param view
     */
    void deleteSubscriber(SubscriberView view);

    /**
     * Получить Subscriber по id
     * @param id
     * @return SubscriberView value
     */
    SubscriberView getSubscriberById(String id);

    /**
     * Получить Subscribers по фильтру
     * @return SubscriberViews values
     */
    Collection<SubscriberView> getAllSubscribers(SubscriberView view);

}
