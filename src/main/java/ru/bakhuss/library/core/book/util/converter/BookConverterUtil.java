package ru.bakhuss.library.core.book.util.converter;

import ru.bakhuss.library.core.book.model.Book;
import ru.bakhuss.library.web.book.view.BookView;

public class BookConverterUtil {
    public static BookView bookToBookView(Book book) {
        BookView bV = new BookView();
        bV.setName(book.getName());
        return bV;
    }

    public static BookView bookToBookViewWithId(Book book) {
        BookView bV = new BookView();
        bV.setId(book.getId().toString());
        bV.setName(book.getName());
        return bV;
    }
}
