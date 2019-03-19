package ru.bakhuss.library.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.person.model.Person;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {

    @Query("select b.writers from Book b where b.id =:bookId")
    List<Person> getWriters(Long bookId);
//    Optional<List<Person>> getWriters(Long bookId);
}
