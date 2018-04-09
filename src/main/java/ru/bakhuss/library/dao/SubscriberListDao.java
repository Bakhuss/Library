package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.SubscriberList;

public interface SubscriberListDao extends CrudRepository<SubscriberList, Long> {
}
