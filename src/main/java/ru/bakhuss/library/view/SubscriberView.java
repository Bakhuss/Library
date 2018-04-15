package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SubscriberView {

    public String personId;

    public String firstName;

    public String secondName;

    public String surname;

    public Date subscribeDate;

    public Date unsubscribeDate;

    public PersonView personView;

    public Set<CatalogView> catalogViews;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{personId:" + personId +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";surname:" + surname +
                ";subscriberDate:" + subscribeDate +
                ";unsubscriberDate:" + unsubscribeDate +
                "}";
    }
}
