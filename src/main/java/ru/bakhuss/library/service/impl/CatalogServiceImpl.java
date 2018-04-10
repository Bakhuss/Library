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
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.CatalogService;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;

import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.function.Function;
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
        try {
            tempC.setTotalCount(Integer.parseInt(view.totalCount));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Total count must be a number(" + view.totalCount + ")");
        }
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
    public ResponseView getCatalogById(String id) {
        Catalog cat = null;
        try {
            cat = catalogDao.findOne(Long.parseLong(id));
            cat.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Catalog id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found catalog by id: " + id);
        } catch (JpaSystemException ex) {
            throw new ResponseErrorException("Error getting catalog by id: " + id);
        }
        CatalogView view = new CatalogView();
        view.id = cat.getId().toString();
        view.bookId = cat.getBook().getId().toString();
        view.bookName = cat.getBook().getName();
        view.description = cat.getDescription();
        Function<Person, PersonView> funcP = p -> {
            PersonView pV = new PersonView();
            pV.id = p.getId().toString();
            pV.firstName = p.getFirstName();
            pV.secondName = p.getSecondName();
            pV.surname = p.getSurname();
            pV.birthday = p.getBirthday();
            return pV;
        };
        view.writers = cat.getBook().getWriters().stream()
                .map(funcP)
                .collect(Collectors.toSet());
        view.totalCount = cat.getTotalCount().toString();
        view.subscribers = new HashSet<>();

        return new ResponseView(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllCatalogs(CatalogView view) {
        Function<Catalog, CatalogView> funcC = c -> {
            CatalogView cV = new CatalogView();
            cV.id = c.getId().toString();
            cV.bookId = c.getBook().getId().toString();
            cV.bookName = c.getBook().getName();
            cV.description = c.getDescription();
            cV.writers = c.getBook().getWriters().stream()
                    .map(PersonView.getFuncPersonToView())
                    .collect(Collectors.toSet());
            cV.totalCount = c.getTotalCount().toString();
            return cV;
        };
        ResponseView viewR = new ResponseView();
        viewR.result = null;
        viewR.data =
                StreamSupport.stream(catalogDao.findAll().spliterator(), false)
                        .map(funcC)
                        .collect(Collectors.toSet());
        return viewR;
    }
}
