package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.LibraryCard;

import java.util.List;
import java.util.Set;

public interface LibraryCardDao extends CrudRepository<LibraryCard, Long> {
    Set<LibraryCard> findByIdIn(List<Long> ids);
}
