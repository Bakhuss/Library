package ru.bakhuss.library.core.person.util;

import ru.bakhuss.library.core.person.model.Person;
import ru.bakhuss.library.core.person.util.converter.PersonConverterUtil;
import ru.bakhuss.library.web.person.view.PersonView;

import java.util.function.Function;

public class PersonUtil {

    public static Function<Person, PersonView> getFuncPersonToView() {
        return PersonConverterUtil::personToPersonView;
    }
}
