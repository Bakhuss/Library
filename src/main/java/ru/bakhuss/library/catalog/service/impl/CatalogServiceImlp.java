package ru.bakhuss.library.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.catalog.dao.CatalogDao;
import ru.bakhuss.library.catalog.model.Catalog;
import ru.bakhuss.library.catalog.service.CatalogService;
import ru.bakhuss.library.error.ResponseErrorException;

@Service
public class CatalogServiceImlp implements CatalogService {
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImlp.class);

    private final CatalogDao catalogDao;

    @Autowired
    public CatalogServiceImlp(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }


    @Override
    @Transactional
    public Long addCatalog(Catalog catalog) {
        Catalog newCatalog = catalogDao.save(catalog);
        log.info(newCatalog.toString());
        return newCatalog.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Catalog getCatalog(Long id) {
        Catalog catalog = catalogDao.findById(id)
                .orElseThrow(() -> new ResponseErrorException(
                        ("Catalog by id = " + id + " not found")
                ));
        log.info(catalog.toString());
        return catalog;
    }

    @Override
    @Transactional
    public boolean updateCatalog(Catalog catalog) {
        Catalog updatedCatalog = catalogDao.save(catalog);
        log.info(updatedCatalog.toString());
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCatalog(Long id) {
        if (!catalogDao.existsById(id))
            throw new ResponseErrorException("Not found catalog by id " + id);
        else {
            catalogDao.deleteById(id);
            log.info("Deleted catalog by id " + id);
        }
        return true;
    }
}
