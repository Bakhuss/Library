package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.Catalog;

public interface CatalogDao extends CrudRepository<Catalog, Long> {
}
