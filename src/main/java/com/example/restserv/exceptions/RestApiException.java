package com.example.restserv.exceptions;

public class RestApiException extends Exception {

    public RestApiException() {
        super("API call failed");
    }

    public RestApiException(String message) {
        super(message);
    }
}
