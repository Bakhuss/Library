package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookView {

    public String id;

    public String name;

    public String totalCount;

    public String availableCount;

    public Set<PersonView> writers;

    public Set<PersonView> subscribers;

    public Set<CatalogView> catalogs;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + id +
                ";name:" + name +
                ";totalCount:" + totalCount +
                ";availableCount:" + availableCount +
                ";writers:" + writers +
                ";catalogs:" + catalogs +
                "}";
    }
}
