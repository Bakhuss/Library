package ru.bakhuss.library.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.person.model.Person;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {

    @Query("select p.writtenBooks from Person p where p.id =:personId")
    List<Book> getWrittenBooks(Long personId);
}
