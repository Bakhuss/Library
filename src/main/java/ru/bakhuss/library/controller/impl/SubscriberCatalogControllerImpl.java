package ru.bakhuss.library.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.SubscriberCatalogController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/subscribcatalog", produces = APPLICATION_JSON_VALUE)
public class SubscriberCatalogControllerImpl implements SubscriberCatalogController {
}
