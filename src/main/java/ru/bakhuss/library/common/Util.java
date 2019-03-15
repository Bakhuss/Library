package ru.bakhuss.library.common;

import ru.bakhuss.library.error.ResponseErrorException;

public class Util {
    public static Long parseLongFromString(String str) {
        Long longFromString;
        try {
            longFromString = Long.parseLong(str);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Id must be a number(" + str + ")");
        }
        return longFromString;
    }
}
