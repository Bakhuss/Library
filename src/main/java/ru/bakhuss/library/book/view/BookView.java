package ru.bakhuss.library.book.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.view.CatalogView;
import ru.bakhuss.library.view.PersonView;

import java.util.Collection;
import java.util.function.Function;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookView {

    public String id;

    public String name;

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
        return "{id:" + getId() +
                ";name:" + getName() +
                "}";
    }
}
