package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Catalog;

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

    public Collection<PersonView> writers;

    public Collection<PersonView> subscribers;

    public String totalCount;

    public String availableCount;


    public static Function<Catalog, CatalogView> getFuncCatalogToView() {
        return c -> {
            CatalogView cV = new CatalogView();
            cV.id = c.getId().toString();
            cV.bookId = c.getBook().getId().toString();
            cV.bookName = c.getBook().getName();
            cV.description = c.getDescription();
            cV.writers = c.getBook().getWriters().stream()
                    .map(PersonView.getFuncPersonToView())
                    .sorted(Comparator.comparing(PersonView::getSurname))
                    .collect(Collectors.toList());
            cV.totalCount = c.getTotalCount().toString();
            return cV;
        };
    }


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
