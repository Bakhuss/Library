package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Person;

import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PersonView {

    public String id;

    public String firstName;

    public String secondName;

    public String surname;

    public Date birthday;

    public Set<BookView> writtenBooks;

    public Set<BookView> subscribeBooks;


    public static Function<Person, PersonView> getFuncPersonToView() {
        Function<Person, PersonView> func = p -> {
            PersonView pV = new PersonView();
            pV.id = p.getId().toString();
            pV.firstName = p.getFirstName();
            pV.secondName = p.getSecondName();
            pV.surname = p.getSurname();
            pV.birthday = p.getBirthday();
            return pV;
        };
        return func;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + id +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";surname:" + surname +
                ";birthday:" + birthday +
                "}";
    }
}
