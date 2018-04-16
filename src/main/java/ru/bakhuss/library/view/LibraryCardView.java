package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LibraryCardView {

    public String id;

    public String subscriberId;

    public String catalogId;

    public Date receiveDate;

    public Date returnDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + id +
                ";subscriberId:" + subscriberId +
                ";catalogId:" + catalogId +
                ";receiveDate:" + receiveDate +
                ";returnDate:" + returnDate +
                "}";
    }
}
