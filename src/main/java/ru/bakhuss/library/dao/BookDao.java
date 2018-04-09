package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
}
