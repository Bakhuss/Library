package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bakhuss.library.dao.SubscriberListDao;
import ru.bakhuss.library.service.SubscriberListService;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class SubscriberListServiceImpl implements SubscriberListService {
    private final Logger log = LoggerFactory.getLogger(SubscriberListServiceImpl.class);

    private final SubscriberListDao subscriberListDao;

    @Autowired
    public SubscriberListServiceImpl(SubscriberListDao subscriberListDao) {
        this.subscriberListDao = subscriberListDao;
    }
}
