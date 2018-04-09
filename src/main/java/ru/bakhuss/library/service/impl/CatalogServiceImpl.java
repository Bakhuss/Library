package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.service.CatalogService;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CatalogServiceImpl implements CatalogService {
    private final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

    private final CatalogDao catalogDao;

    @Autowired
    public CatalogServiceImpl(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }
}
