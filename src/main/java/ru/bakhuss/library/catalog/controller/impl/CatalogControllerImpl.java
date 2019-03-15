package ru.bakhuss.library.catalog.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.catalog.controller.CatalogController;
import ru.bakhuss.library.catalog.service.CatalogService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/catalog", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {
    private final Logger log = LoggerFactory.getLogger(CatalogControllerImpl.class);

    private final CatalogService catalogService;

    @Autowired
    public CatalogControllerImpl(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
