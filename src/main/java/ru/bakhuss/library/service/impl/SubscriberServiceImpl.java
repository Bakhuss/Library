package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.dao.SubscriberCatalogDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.model.Subscriber;
import ru.bakhuss.library.service.SubscriberService;
import ru.bakhuss.library.view.ResponseView;
import ru.bakhuss.library.view.SubscriberView;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class SubscriberServiceImpl implements SubscriberService {
    private final Logger log = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberDao subscriberDao;
    private final PersonDao personDao;
    private final SubscriberCatalogDao subscriberCatalogDao;

    @Autowired
    public SubscriberServiceImpl(SubscriberDao subscriberDao,
                                 PersonDao personDao,
                                 SubscriberCatalogDao subscriberCatalogDao) {
        this.subscriberDao = subscriberDao;
        this.personDao = personDao;
        this.subscriberCatalogDao = subscriberCatalogDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView addSubscriber(SubscriberView view) {
        Subscriber sub = null;
        Person person = null;
        try {
            person = personDao.findOne(Long.parseLong(view.personId));

            /*
             * Проверка на NPE
             */
            person.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Person id must be a number(" + view.personId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found person by id: " + view.personId);
        }

        try {
            sub = subscriberDao.findOne(Long.parseLong(view.personId));
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Subscriber id must be a number(" + view.personId + ")");
        }
        if (sub == null) {
            sub = new Subscriber();
            sub.setPerson(person);
            sub.setSubscribeDate(view.subscribeDate);
        } else {
            sub.setSubscribeDate(view.subscribeDate);
            sub.setUnsubscribeDate(null);
        }

        try {
            subscriberDao.save(sub);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving subscriber");
        }
        log.info(sub.toString());
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView updateSubscriber(SubscriberView view) {
        Subscriber sub = null;
        try {
            sub = subscriberDao.findOne(Long.parseLong(view.personId));
            /*
             * Проверка на NPE
             */
            sub.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("id must be a number");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + view.personId);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting subscriber");
        }

        log.info(sub.toString());


        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView deleteSubscriber(SubscriberView view) {
        Subscriber sub = null;
        try {
            subscriberDao.findOne(Long.parseLong(view.personId));
            /*
             * Проверка на NPE
             */
            sub.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Subscriber id must be a number(" + view.personId + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + view.personId);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        sub.setUnsubscribeDate(view.unsubscribeDate);

        try {
            subscriberDao.save(sub);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error deleting subscriber by id: " + view.personId);
        }
        return new ResponseView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getSubscriberById(String id) {
        Subscriber sub = null;
        try {
            sub = subscriberDao.findOne(Long.parseLong(id));

            /*
             * Проверка на NPE
             */
            sub.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("id must be a number");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting data");
        }
        SubscriberView subV = new SubscriberView();
        subV.personId = sub.getId().toString();
        subV.firstName = sub.getPerson().getFirstName();
        subV.secondName = sub.getPerson().getSecondName();
        subV.surname = sub.getPerson().getSurname();
        subV.subscribeDate = sub.getSubscribeDate();
        subV.unsubscribeDate = sub.getUnsubscribeDate();

        return new ResponseView(subV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView getAllSubscribers(SubscriberView view) {
        Function<Subscriber, SubscriberView> func = s -> {
            SubscriberView subV = new SubscriberView();
            subV.personId = s.getId().toString();
            subV.firstName = s.getPerson().getFirstName();
            subV.secondName = s.getPerson().getSecondName();
            subV.surname = s.getPerson().getSurname();
            subV.subscribeDate = s.getSubscribeDate();
            subV.unsubscribeDate = s.getUnsubscribeDate();
            return subV;
        };
        ResponseView viewR = new ResponseView();
        viewR.result = null;
        try {
            viewR.data =
                    StreamSupport.stream(subscriberDao.findAll().spliterator(), false)
                            .map(func).collect(Collectors.toSet());
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting subscribers");
        }

        return viewR;
    }
}
