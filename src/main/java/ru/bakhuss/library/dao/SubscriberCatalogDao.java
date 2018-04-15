package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.SubscriberCatalog;

public interface SubscriberCatalogDao extends CrudRepository<SubscriberCatalog, Long> {
}
