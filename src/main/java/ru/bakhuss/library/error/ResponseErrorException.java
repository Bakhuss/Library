package ru.bakhuss.library.error;

public class ResponseErrorException extends RuntimeException {

    public ResponseErrorException(String error) {
        super(error);
    }
}
