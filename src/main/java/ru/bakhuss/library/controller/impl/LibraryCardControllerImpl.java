package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.LibraryCardController;
import ru.bakhuss.library.service.LibraryCardService;
import ru.bakhuss.library.view.LibraryCardView;
import ru.bakhuss.library.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/librarycard", produces = APPLICATION_JSON_VALUE)
public class LibraryCardControllerImpl implements LibraryCardController {
    private final LibraryCardService libraryCardService;

    @Autowired
    private LibraryCardControllerImpl(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addCatalog(@RequestBody LibraryCardView view) {
        libraryCardService.addLibraryCard(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deleteCatalog(@RequestBody LibraryCardView view) {
        libraryCardService.updateLibraryCard(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/list")
    public ResponseView getAllLibraryCards(@RequestBody LibraryCardView view) {
        return new ResponseView(libraryCardService.getAll(view));
    }
}
