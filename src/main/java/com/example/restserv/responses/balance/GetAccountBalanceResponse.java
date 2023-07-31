package com.example.restserv.responses.balance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAccountBalanceResponse {

    private String status;
    private List<Error> errors;
    private GetAccountBalancePayloadResponse payload;

    public GetAccountBalanceResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public GetAccountBalancePayloadResponse getPayload() {
        return payload;
    }

    public void setPayload(GetAccountBalancePayloadResponse payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAccountBalanceResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", error=").append(errors);
        sb.append(", payload=").append(payload);
        sb.append('}');
        return sb.toString();
    }
}
