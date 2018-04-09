package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Subscriber;

@Repository
public interface SubscriberDao extends CrudRepository<Subscriber, Long> {
}
