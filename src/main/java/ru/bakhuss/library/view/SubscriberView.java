package ru.bakhuss.library.view;

import java.util.Date;
import java.util.Set;

public class SubscriberView {

    public String personId;

    public Date subscribeDate;

    public Date unsubscribeDate;

    public PersonView personView;

    public Set<CatalogView> catalogViews;
}
