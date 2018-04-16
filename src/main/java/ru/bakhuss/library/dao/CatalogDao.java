package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.Catalog;

import java.util.List;
import java.util.Set;

public interface CatalogDao extends CrudRepository<Catalog, Long> {
    Set<Catalog> findByIdIn(List<Long> ids);
}
