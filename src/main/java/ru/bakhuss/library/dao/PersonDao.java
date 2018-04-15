package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Person;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {

    Set<Person> findByIdIn(List<Long> ids);

}
