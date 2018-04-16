package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.LibraryCardDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.model.LibraryCard;
import ru.bakhuss.library.model.Subscriber;
import ru.bakhuss.library.service.LibraryCardService;
import ru.bakhuss.library.view.LibraryCardView;

import java.util.Collection;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class LibraryCardServiceImpl implements LibraryCardService {
    private final Logger log = LoggerFactory.getLogger(LibraryCardServiceImpl.class);

    private final LibraryCardDao libraryCardDao;
    private final SubscriberDao subscriberDao;
    private final CatalogDao catalogDao;

    @Autowired
    public LibraryCardServiceImpl(LibraryCardDao libraryCardDao,
                                  SubscriberDao subscriberDao,
                                  CatalogDao catalogDao) {
        this.libraryCardDao = libraryCardDao;
        this.subscriberDao = subscriberDao;
        this.catalogDao = catalogDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addLibraryCard(LibraryCardView view) {
        LibraryCard sc = new LibraryCard();
        Subscriber sub = null;
        Catalog cat = null;
        try {
            sub = subscriberDao.findOne(Long.parseLong(view.subscriberId));

            /*
             * Проверка на NPE
             */
            sub.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Subscriber id must be a number(" + view.subscriberId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + view.subscriberId);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting subscriber");
        }
        try {
            cat = catalogDao.findOne(Long.parseLong(view.catalogId));

            /*
             * Проверка на NPE
             */
            cat.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Subscriber id must be a number(" + view.catalogId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + view.catalogId);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting catalog");
        }
        sc.setSubscriber(sub);
        sc.setCatalog(cat);
        sc.setReceiveDate(view.receiveDate);

        try {
            libraryCardDao.save(sc);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving LibraryCard");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateLibraryCard(LibraryCardView view) {
        LibraryCard sc = null;
        try {
            sc = libraryCardDao.findOne(Long.parseLong(view.id));
            /*
             * Проверка на NPE
             */
            sc.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + view.id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber-catalog by id: " + view.id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        sc.setReturnDate(view.returnDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public LibraryCardView getBySubscriberId(String id) {
        LibraryCard sc = null;
        try {
            sc = libraryCardDao.findOne(Long.parseLong(id));
            sc.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber-catalog by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        LibraryCardView scv = new LibraryCardView();
        scv.id = sc.getId().toString();
        scv.subscriberId = sc.getSubscriber().getId().toString();
        scv.catalogId = sc.getCatalog().getId().toString();
        scv.receiveDate = sc.getReceiveDate();
        scv.returnDate = sc.getReturnDate();
        return scv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public LibraryCardView getByCatalogId(String id) {
        LibraryCard sc = null;
        try {
            sc = libraryCardDao.findOne(Long.parseLong(id));
            sc.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber-catalog by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        LibraryCardView scv = new LibraryCardView();
        scv.id = sc.getId().toString();
        scv.subscriberId = sc.getSubscriber().getId().toString();
        scv.catalogId = sc.getCatalog().getId().toString();
        scv.receiveDate = sc.getReceiveDate();
        scv.returnDate = sc.getReturnDate();
        return scv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<LibraryCardView> getAll(LibraryCardView view) {

        return null;
    }
}
