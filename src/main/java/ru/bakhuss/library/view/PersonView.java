package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Person;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PersonView {

    public String id;

    public String firstName;

    public String secondName;

    public String surname;

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date birthday;

    public Collection<BookView> writtenBooks;

    public Collection<BookView> subscribeBooks;

    public String getSurname() {
        return surname;
    }

    public static Function<Person, PersonView> getFuncPersonToView() {
        return p -> {
            PersonView pV = new PersonView();
            pV.id = p.getId().toString();
            pV.firstName = p.getFirstName();
            pV.secondName = p.getSecondName();
            pV.surname = p.getSurname();
            pV.birthday = p.getBirthday();
            return pV;
        };
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
