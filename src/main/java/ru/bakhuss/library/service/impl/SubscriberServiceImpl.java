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
import ru.bakhuss.library.dao.LibraryCardDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.model.Subscriber;
import ru.bakhuss.library.service.SubscriberService;
import ru.bakhuss.library.view.SubscriberView;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class SubscriberServiceImpl implements SubscriberService {
    private final Logger log = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberDao subscriberDao;
    private final PersonDao personDao;
    private final LibraryCardDao libraryCardDao;

    @Autowired
    public SubscriberServiceImpl(SubscriberDao subscriberDao,
                                 PersonDao personDao,
                                 LibraryCardDao libraryCardDao) {
        this.subscriberDao = subscriberDao;
        this.personDao = personDao;
        this.libraryCardDao = libraryCardDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addSubscriber(SubscriberView view) {
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
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting person from db");
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
        Subscriber newSub = null;
        try {
            newSub = subscriberDao.save(sub);
            newSub.getId();
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving subscriber");
        }
        log.info(newSub.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateSubscriber(SubscriberView view) {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteSubscriber(SubscriberView view) {
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
            throw new ResponseErrorException("Error requesting subscriber from db");
        }
        sub.setUnsubscribeDate(view.unsubscribeDate);
        Subscriber newSub = null;
        try {
            newSub = subscriberDao.save(sub);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error removing subscriber by id: " + view.personId);
        }
        log.info(newSub.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public SubscriberView getSubscriberById(String id) {
        Subscriber sub = null;
        try {
            sub = subscriberDao.findOne(Long.parseLong(id));
            /*
             * Проверка на NPE
             */
            sub.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Subscriber id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found subscriber by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting subscriber from db");
        }
        SubscriberView subView = new SubscriberView();
        subView.personId = sub.getId().toString();
        subView.firstName = sub.getPerson().getFirstName();
        subView.secondName = sub.getPerson().getSecondName();
        subView.surname = sub.getPerson().getSurname();
        subView.subscribeDate = sub.getSubscribeDate();
        subView.unsubscribeDate = sub.getUnsubscribeDate();
        return subView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<SubscriberView> getAllSubscribers(SubscriberView view) {
        List<SubscriberView> subView = null;
        try {
            subView =
                    StreamSupport.stream(subscriberDao.findAll().spliterator(), false)
                            .map(SubscriberView.getFuncSubToView())
                            .sorted(Comparator.comparing(SubscriberView::getSurname))
                            .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting subscribers");
        }
        return subView;
    }
}
