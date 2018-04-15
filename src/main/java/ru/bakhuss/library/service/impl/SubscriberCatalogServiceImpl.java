package ru.bakhuss.library.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.SubscriberCatalogDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.model.Subscriber;
import ru.bakhuss.library.model.SubscriberCatalog;
import ru.bakhuss.library.service.SubscriberCatalogService;
import ru.bakhuss.library.view.PersonView;
import ru.bakhuss.library.view.ResponseView;
import ru.bakhuss.library.view.SubscriberCatalogView;
import ru.bakhuss.library.view.SubscriberView;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class SubscriberCatalogServiceImpl implements SubscriberCatalogService {
    private final Logger log = LoggerFactory.getLogger(SubscriberCatalogServiceImpl.class);

    private final SubscriberCatalogDao subscriberCatalogDao;
    private final SubscriberDao subscriberDao;
    private final CatalogDao catalogDao;

    @Autowired
    public SubscriberCatalogServiceImpl(SubscriberCatalogDao subscriberCatalogDao,
                                        SubscriberDao subscriberDao,
                                        CatalogDao catalogDao) {
        this.subscriberCatalogDao = subscriberCatalogDao;
        this.subscriberDao = subscriberDao;
        this.catalogDao = catalogDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addSubscriberCatalog(SubscriberCatalogView view) {
        SubscriberCatalog sc = new SubscriberCatalog();
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
            subscriberCatalogDao.save(sc);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving SubscriberCatalog");
        }

        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updateSubscriberCatalog(SubscriberCatalogView view) {
        SubscriberCatalog sc = null;
        try {
            sc = subscriberCatalogDao.findOne(Long.parseLong(view.id));
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
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getBySubscriberId(String id) {
        SubscriberCatalog sc = null;
        try {
            sc = subscriberCatalogDao.findOne(Long.parseLong(id));
            sc.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber-catalog by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        SubscriberCatalogView scv = new SubscriberCatalogView();
        scv.id = sc.getId().toString();
        scv.subscriberId = sc.getSubscriber().getId().toString();
        scv.catalogId = sc.getCatalog().getId().toString();
        scv.receiveDate = sc.getReceiveDate();
        scv.returnDate = sc.getReturnDate();
        return new ResponseView(scv);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getByCatalogId(String id) {
        SubscriberCatalog sc = null;
        try {
            sc = subscriberCatalogDao.findOne(Long.parseLong(id));
            sc.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber-catalog by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        SubscriberCatalogView scv = new SubscriberCatalogView();
        scv.id = sc.getId().toString();
        scv.subscriberId = sc.getSubscriber().getId().toString();
        scv.catalogId = sc.getCatalog().getId().toString();
        scv.receiveDate = sc.getReceiveDate();
        scv.returnDate = sc.getReturnDate();
        return new ResponseView(scv);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAll(SubscriberCatalogView view) {
        ObjectMapper mapper = new ObjectMapper();

        return null;
    }
}
