package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Subscriber;

import java.util.List;
import java.util.Set;

@Repository
public interface SubscriberDao extends CrudRepository<Subscriber, Long> {
    Set<Subscriber> findByIdIn(List<Long> ids);
}
