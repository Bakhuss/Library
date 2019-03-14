package ru.bakhuss.library.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakhuss.library.book.model.Book;

public interface BookDao extends JpaRepository<Book, Long> {
}
