package ru.bakhuss.library.catalog.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.catalog.controller.CatalogController;
import ru.bakhuss.library.catalog.model.Catalog;
import ru.bakhuss.library.catalog.service.CatalogService;
import ru.bakhuss.library.catalog.view.CatalogView;
import ru.bakhuss.library.common.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bakhuss.library.common.Util.parseIntegerFromString;

@RestController
@RequestMapping(value = "/api/catalog", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {
    private final Logger log = LoggerFactory.getLogger(CatalogControllerImpl.class);

    private final CatalogService catalogService;

    @Autowired
    public CatalogControllerImpl(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @Override
    @PostMapping(value = "/save")
    public ResponseView addCatalog(CatalogView view) {
        log.info(view.toString());
        Catalog catalog = new Catalog();
        catalog.setIsbn(view.getIsbn());
        catalog.setDescription(view.getDescription());
        catalog.setTotalCount(parseIntegerFromString(view.getTotalCount()));
        catalogService.addCatalog(catalog);
        return new ResponseView(true);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getCatalog(@PathVariable String id) {

        return null;
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseView updateCatalog(CatalogView view) {
        return null;
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseView deleteCatalog(CatalogView view) {
        return null;
    }
}
