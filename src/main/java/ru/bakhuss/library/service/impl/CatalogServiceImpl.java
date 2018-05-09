package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.service.CatalogService;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.FilterView;
import ru.bakhuss.library.view.PersonView;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Long addCatalog(CatalogView view) {
        if (view.bookId.isEmpty()) throw new ResponseErrorException("Book is required parameter");
        Catalog tempC = new Catalog();
        Book book = null;
        try {
            book = bookDao.findOne(Long.parseLong(view.bookId));
            /*
             * Проверка на NPE
             */
            book.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Book id must be a number(" + view.bookId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found book by id: " + view.bookId);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting book by id: " + view.bookId + " from db");
        }
        tempC.setBook(book);
        tempC.setDescription(view.description);
        try {
            tempC.setTotalCount(Integer.parseInt(view.totalCount));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Total count must be a number(" + view.totalCount + ")");
        }
        Catalog newC = null;
        try {
            newC = catalogDao.save(tempC);
            newC.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving new catalog");
        }
        log.info(newC.toString());
        return newC.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateCatalog(CatalogView view) {
        Catalog cat = null;
        try {
            cat = catalogDao.findOne(Long.parseLong(view.id));
            /*
             * Проверка на NPE
             */
            cat.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Catalog id must be a number (" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found catalog by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting catalog by id: " + view.id);
        }
        try {
            cat.setTotalCount(Integer.valueOf(view.totalCount));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Total count must be a number (" + view.totalCount + ")");
        }
        cat.setDescription(view.description);
        try {
            catalogDao.save(cat);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error updating catalog by id: " + view.id);
        }
        log.info(cat.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteCatalog(CatalogView view) {
        try {
            Long id = Long.parseLong(view.id);
            /*
             * Проверка на NPE
             */
            catalogDao.findOne(id).getId();
            catalogDao.delete(Long.parseLong(view.id));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Catalog id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found catalog by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error deleting catalog by id: " + view.id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public CatalogView getCatalogById(String id) {
        Catalog cat = null;
        try {
            cat = catalogDao.findOne(Long.parseLong(id));
            /*
             * Проверка на NPE
             */
            cat.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Catalog id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found catalog by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting catalog by id: " + id + " from db");
        }
        CatalogView view = new CatalogView();
        view.id = cat.getId().toString();
        view.bookId = cat.getBook().getId().toString();
        view.bookName = cat.getBook().getName();
        view.description = cat.getDescription();
//        view.writers = cat.getBook().getWriters().stream()
//                .map(PersonView.getFuncPersonToView())
//                .sorted(Comparator.comparing(PersonView::getSurname))
//                .collect(Collectors.toList());
        view.totalCount = cat.getTotalCount().toString();

        view.subscribers = new HashSet<>();
        log.info(view.toString());
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<CatalogView> getAllCatalogs(FilterView view) {
        List<CatalogView> catalogV = null;
        int page = Integer.parseInt(view.page);
        int fetchSize = Integer.parseInt(view.fetchSize);
        String props = view.orderSort;
        Sort.Direction direct = null;
        switch (view.orderSort) {
            case ("asc"):
                direct = Sort.Direction.ASC;
                break;
            case ("desc"):
                direct = Sort.Direction.DESC;
                break;
            default:
                direct = Sort.Direction.ASC;
        }
        Sort sort = new Sort(direct, props);
        Pageable pageable = new PageRequest(page, fetchSize, sort);
        try {
            Page<Catalog> catalogPage = catalogDao.findAll(pageable);
            List<Catalog> listCatalg = catalogPage.getContent();
            catalogV = listCatalg.stream()
                    .map(CatalogView.getFuncCatalogToView())
                    .collect(Collectors.toList());
            log.info("------------size: " + catalogV.size());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new ResponseErrorException("Error requesting catalogs from db");
        }
        log.info(catalogV.toString());
        return catalogV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public FilterView getCatalogsCount() {
        Long count = null;
        try {
            count = catalogDao.count();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting catalogs count");
        }
        FilterView filterV = new FilterView();
        filterV.count = String.valueOf(count);
        log.info("count: " + filterV.count);
        return filterV;
    }
}
