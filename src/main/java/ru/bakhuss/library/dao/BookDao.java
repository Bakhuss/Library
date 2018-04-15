package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.model.Book;

import java.util.List;
import java.util.Set;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    Set<Book> findByIdIn(List<Long> ids);
}
