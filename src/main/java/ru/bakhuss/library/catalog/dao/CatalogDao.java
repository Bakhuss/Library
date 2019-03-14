package ru.bakhuss.library.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakhuss.library.catalog.model.Catalog;

public interface CatalogDao extends JpaRepository<Catalog, Long> {
}
