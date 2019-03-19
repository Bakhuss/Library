package ru.bakhuss.library.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.person.model.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {
}
