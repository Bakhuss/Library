package ru.bakhuss.library.book.util.converter;

import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.book.view.BookView;

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
