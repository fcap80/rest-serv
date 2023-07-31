package com.example.restserv.exceptions;

import org.springframework.web.client.RestClientResponseException;

public class RestApiException extends Exception {
    private RestClientResponseException restClientResponseException;

    public RestApiException() {
        super("API call failed");
    }

    public RestApiException(String message) {
        super(message);
    }

    public RestApiException(RestClientResponseException restClientResponseException) {
        super("API call failed");
        this.restClientResponseException = restClientResponseException;
    }

    public RestClientResponseException getRestClientResponseException() {
        return restClientResponseException;
    }
}
