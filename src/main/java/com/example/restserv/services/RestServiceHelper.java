package com.example.restserv.services;

import com.example.restserv.requests.ExecuteWireTransferRequest;
import com.example.restserv.utils.SharedConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class RestServiceHelper {

    @Value("${rest-base-url}")
    private String restBaseUrl;

    @Value("${auth-schema}")
    private String authSchema;

    @Value("${api-key}")
    private String apiKeyHeaderKey;

    private final ObjectMapper objectMapper;

    public RestServiceHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private HttpHeaders composeBasicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SharedConstants.RestParams.AUTH_SCHEMA_HEADER_KEY, authSchema);
        headers.add(SharedConstants.RestParams.API_KEY_HEADER_KEY, apiKeyHeaderKey);
        return headers;
    }

    public HttpEntity<Void> createBaseRequestEntity() {
        return new HttpEntity<>(composeBasicHeaders());
    }

    public String composeBaseUrlForAccounts() {
        return restBaseUrl + "/" + SharedConstants.RestParams.ACCOUNTS_URI;
    }

    public HttpEntity<String> createRequestEntityForMoneyTransfer(ExecuteWireTransferRequest executeWireTransferRequest) throws JsonProcessingException {
        HttpHeaders httpHeaders = composeBasicHeaders();
        httpHeaders.add("X-Time-Zone", "");
        httpHeaders.add("Content-Type", "application/json");
        String requestString = objectMapper.writeValueAsString(executeWireTransferRequest);
        return new HttpEntity<>(requestString, httpHeaders);
    }
}
