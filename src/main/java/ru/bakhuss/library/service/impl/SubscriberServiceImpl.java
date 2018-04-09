package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.service.SubscriberService;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class SubscriberServiceImpl implements SubscriberService {
    private final Logger log = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberDao subscriberDao;

    @Autowired
    public SubscriberServiceImpl(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }
}
