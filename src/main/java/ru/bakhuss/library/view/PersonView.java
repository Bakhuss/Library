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
        Function<Person, PersonView> func = person1 -> {
            PersonView pV = new PersonView();
            pV.id = person1.getId().toString();
            pV.firstName = person1.getFirstName();
            pV.secondName = person1.getSecondName();
            pV.surname = person1.getSurname();
            pV.birthday = person1.getBirthday();
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
