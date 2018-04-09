package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {
}
