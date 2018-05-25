package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.ImageController;
import ru.bakhuss.library.service.ImageService;
import ru.bakhuss.library.view.ImageView;
import ru.bakhuss.library.view.ResponseView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/image", produces = APPLICATION_JSON_VALUE)
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageControllerImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addImage(ImageView view) {
        imageService.addImage(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deleteImage(ImageView view) {
        imageService.deleteImage(view);
        return new ResponseView(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getImageById(@PathVariable String id) {
        return new ResponseView(imageService.getImageById(id));
    }
}
