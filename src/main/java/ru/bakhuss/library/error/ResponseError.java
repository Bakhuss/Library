package ru.bakhuss.library.error;

public class ResponseError {
    public String error;

    public ResponseError(String errMsg) {
        this.error = errMsg;
    }
}
