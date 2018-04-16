package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Subscriber;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SubscriberView {

    public String personId;

    public String firstName;

    public String secondName;

    public String surname;

    public Date subscribeDate;

    public Date unsubscribeDate;

    public PersonView person;

    public Collection<CatalogView> catalogs;


    public String getSurname() {
        return surname;
    }

    public static Function<Subscriber, SubscriberView> getFuncSubToView() {
        return s -> {
            SubscriberView view = new SubscriberView();
            view.personId = s.getId().toString();
            view.firstName = s.getPerson().getFirstName();
            view.secondName = s.getPerson().getSecondName();
            view.surname = s.getPerson().getSurname();
            view.subscribeDate = s.getSubscribeDate();
            view.unsubscribeDate = s.getUnsubscribeDate();
            return view;
        };
    }

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
