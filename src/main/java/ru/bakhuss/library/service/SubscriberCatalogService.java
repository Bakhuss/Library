package ru.bakhuss.library.service;

import ru.bakhuss.library.view.ResponseView;
import ru.bakhuss.library.view.SubscriberCatalogView;

/**
 * Сервис для работы с SubscriberCatalog
 */
public interface SubscriberCatalogService {

    /**
     * Сохранить SubscriberCatalog
     * @param view
     * @return success value
     */
    ResponseView addSubscriberCatalog(SubscriberCatalogView view);

    /**
     * Обновить SubscriberCatalog
     * @param view
     * @return success value
     */
    ResponseView updateSubscriberCatalog(SubscriberCatalogView view);

    /**
     * Получить SubscriberCatalog по subscriberId
     * @param id
     * @return JSON SubscriberCatalog value
     */
    ResponseView getBySubscriberId(String id);

    /**
     * Получить SubscriberCatalog по catalogId
     * @param id
     * @return JSON SubscriberCatalog value
     */
    ResponseView getByCatalogId(String id);

    /**
     * Получить все SubscriberCatalog по фильтру
     * @param view
     * @return JSON subscriberCatalogs values
     */
    ResponseView getAll(SubscriberCatalogView view);

}
