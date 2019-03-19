package ru.bakhuss.library.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bakhuss.library.catalog.model.Catalog;

@Repository
public interface CatalogDao extends JpaRepository<Catalog, Long> {
}
