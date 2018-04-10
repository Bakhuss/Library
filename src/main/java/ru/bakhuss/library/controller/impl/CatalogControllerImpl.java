package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.CatalogController;
import ru.bakhuss.library.service.CatalogService;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/catalog", produces = APPLICATION_JSON_VALUE)
public class CatalogControllerImpl implements CatalogController {
    private final CatalogService catalogService;

    @Autowired
    private CatalogControllerImpl(CatalogService service) {
        this.catalogService = service;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addCatalog(@RequestBody CatalogView view) {
        return catalogService.addCatalog(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/update")
    public ResponseView updateCatalog(@RequestBody CatalogView view) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deleteCatalog(@RequestBody CatalogView view) {
        return catalogService.deleteCatalog(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getCatalogById(@PathVariable("id") String id) {
        return catalogService.getCatalogById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/list")
    public ResponseView getCatalogs(@RequestBody CatalogView view) {
        return catalogService.getAllCatalogs(view);
    }
}
