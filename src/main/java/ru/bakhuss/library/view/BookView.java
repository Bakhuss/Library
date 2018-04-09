package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookView {

    public String id;

    public String name;

    public String description;

    public String totalCount;

    public String availableCount;

    public Set<PersonView> writers;

    public Set<PersonView> subscribers;
}
