package ru.bakhuss.library.view;

import java.util.Set;

public class CatalogView {

    public String id;

    public String description;

    public String totalCount;

    public String availableCount;

    public String bookName;

    public Set<PersonView> writers;

    public Set<PersonView> subscribers;
}
