package ru.bakhuss.library.catalog.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Catalog;
import ru.bakhuss.library.view.PersonView;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CatalogView {

    public String id;

    public String bookId;

    public String bookName;

    public String description;

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
                ";totalCount:" + totalCount +
                ";availableCount:" + availableCount +
                "}";
    }
}
