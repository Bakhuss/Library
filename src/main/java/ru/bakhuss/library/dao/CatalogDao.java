package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.bakhuss.library.model.Catalog;

import java.util.List;
import java.util.Set;

public interface CatalogDao extends CrudRepository<Catalog, Long>, PagingAndSortingRepository<Catalog, Long> {
    Set<Catalog> findByIdIn(List<Long> ids);
}
