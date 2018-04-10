package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CatalogView {

    public String id;

    public String bookId;

    public String bookName;

    public String description;

    public Set<PersonView> writers;

    public Set<PersonView> subscribers;

    public String totalCount;

    public String availableCount;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + id +
                ";bookId:" + bookId +
                ";bookName:" + bookName +
                ";description:" + description +
                ";writers:" + writers +
                ";subscribers:" + subscribers +
                ";totalCount:" + totalCount +
                ";availableCount:" + availableCount +
                "}";
    }
}
