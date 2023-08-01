package com.example.restserv.exceptions;

import org.springframework.web.client.RestClientResponseException;

public class RestApiException extends Exception {

    public RestApiException() {
        super("API call failed");
    }

    public RestApiException(String message) {
        super(message);
    }
}
