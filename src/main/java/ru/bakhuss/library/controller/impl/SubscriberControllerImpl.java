package ru.bakhuss.library.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.library.controller.SubscriberController;
import ru.bakhuss.library.service.SubscriberService;
import ru.bakhuss.library.view.ResponseView;
import ru.bakhuss.library.view.SubscriberView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/subscriber", produces = APPLICATION_JSON_VALUE)
public class SubscriberControllerImpl implements SubscriberController {
    private final SubscriberService subscriberService;

    @Autowired
    private SubscriberControllerImpl(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/save")
    public ResponseView addSubscriber(@RequestBody SubscriberView view) {
        return subscriberService.addSubscriber(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping
    public ResponseView updateSubscriber(@RequestBody SubscriberView view) {
        return subscriberService.updateSubscriber(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/delete")
    public ResponseView deleteSubscriber(@RequestBody SubscriberView view) {
        return subscriberService.deleteSubscriber(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    public ResponseView getSubscriberById(@PathVariable("id") String id) {
        return subscriberService.getSubscriberById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping(value = "/list")
    public ResponseView getSubscribers(@RequestBody SubscriberView view) {
        return subscriberService.getAllSubscribers(view);
    }
}
