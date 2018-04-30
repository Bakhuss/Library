package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FilterView {

    public String count;

    public String page;

    public String fetchSize;

    public String orderSort;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{count:" + count +
               ";startPage:" + page +
               ";fetchSize:" + fetchSize +
               ";orderSort:" + orderSort +
               "}";
    }

}
