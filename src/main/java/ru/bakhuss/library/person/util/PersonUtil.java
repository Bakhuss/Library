package ru.bakhuss.library.person.util;

import ru.bakhuss.library.person.model.Person;
import ru.bakhuss.library.person.util.converter.PersonConverterUtil;
import ru.bakhuss.library.person.view.PersonView;

import java.util.function.Function;

public class PersonUtil {

    public static Function<Person, PersonView> getFuncPersonToView() {
        return PersonConverterUtil::personToPersonView;
    }
}
