package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.service.CatalogService;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.ResponseView;

import javax.validation.constraints.Null;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CatalogServiceImpl implements CatalogService {
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CatalogDao catalogDao;
    private final BookDao bookDao;

    @Autowired
    public CatalogServiceImpl(CatalogDao catalogDao, BookDao bookDao) {
        this.catalogDao = catalogDao;
        this.bookDao = bookDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addCatalog(CatalogView view) {
        Catalog tempC = new Catalog();
        Book b = null;
        try {
            b = bookDao.findOne(Long.parseLong(view.bookId));

            /*
             * Проверка на NPE
             */
            b.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + view.bookId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + view.bookId);
        }
        tempC.setBook(b);
        tempC.setDescription(view.description);
        tempC.setTotalCount(Integer.parseInt(view.totalCount));
        Catalog newC = null;
        try {
            newC = catalogDao.save(tempC);
        } catch (JpaSystemException e) {
            throw new ResponseErrorException("Error saving new catalog");
        }
        log.info(newC.toString());

        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updateCatalog(CatalogView view) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView deleteCatalog(CatalogView catalogView) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getCatalogById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllCatalogs() {
        return null;
    }
}
