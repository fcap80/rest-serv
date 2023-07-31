package com.example.restserv.responses.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAccountResponse {

    private String status;
    private List<Error> errors;
    private GetAccountPayloadResponse payload;

    public GetAccountResponse() {
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

    public GetAccountPayloadResponse getPayload() {
        return payload;
    }

    public void setPayload(GetAccountPayloadResponse payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAccountResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", error=").append(errors);
        sb.append(", payload=").append(payload);
        sb.append('}');
        return sb.toString();
    }
}
