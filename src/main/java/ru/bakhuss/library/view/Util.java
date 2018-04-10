package ru.bakhuss.library.view;

import ru.bakhuss.library.model.Person;

import java.util.function.Function;

public class Util {

    public static <T, R>Function getFunction(T t, R r) {

        Function<Person, PersonView> function = person -> {
            PersonView v = new PersonView();

            return v;
        };

        return function;
    }

}
