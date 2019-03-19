package ru.bakhuss.library.catalog.util.converter;

import ru.bakhuss.library.catalog.model.Catalog;
import ru.bakhuss.library.catalog.view.CatalogView;

public class CatalogConverterUtil {
    public static CatalogView catalogToCatalogView(Catalog catalog) {
        CatalogView cV = new CatalogView();
        cV.setId(catalog.getId().toString());
        cV.setBookId(catalog.getBook().getId().toString());
        cV.setBookName(catalog.getBook().getName());
        cV.setIsbn(catalog.getIsbn());
        cV.setDescription(catalog.getDescription());
        cV.setTotalCount(catalog.getTotalCount().toString());
        return cV;
    }
}
