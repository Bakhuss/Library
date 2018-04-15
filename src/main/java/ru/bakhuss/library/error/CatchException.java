package ru.bakhuss.library.error;

public class CatchException {

    public static Long numbFormExc(Class clazz, String id) {
        String className = clazz.getSimpleName();
        Long idL = null;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException(className + " id must be a number(" + id + ")");
        }
        return idL;
    }
}
