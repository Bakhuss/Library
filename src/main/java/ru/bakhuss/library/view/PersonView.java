package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PersonView {

    public String id;

    public String firstName;

    public String secondName;

    public String surname;

    public Date birthday;

    public Set<BookView> writtenBooks;

    public Set<BookView> subscribeBooks;
}
