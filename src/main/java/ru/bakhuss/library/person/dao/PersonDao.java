package ru.bakhuss.library.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakhuss.library.person.model.Person;

public interface PersonDao extends JpaRepository<Person, Long> {
}
