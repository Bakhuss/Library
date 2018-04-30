package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Book;

import java.util.Collection;
import java.util.function.Function;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookView {

    public String id;

    public String name;

    public Collection<PersonView> writers;

    public Collection<CatalogView> catalogs;


    public String getName() {
        return name;
    }

    public static Function<Book, BookView> getFuncBookToView() {
        return b -> {
            BookView bV = new BookView();
            bV.id = b.getId().toString();
            bV.name = b.getName();
            return bV;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + id +
                ";name:" + name +
                ";writers:" + writers +
                ";catalogs:" + catalogs +
                "}";
    }
}
