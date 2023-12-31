package com.example.restserv.responses.balance;

import com.example.restserv.model.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAccountBalanceResponse {

    private String status;
    private List<Error> errors;
    private GetAccountBalancePayloadResponse payload;

    public GetAccountBalanceResponse() {
    }

    public static GetAccountBalanceResponse failWithOneError(String status, String errorCode, String errorDescription) {
        GetAccountBalanceResponse getAccountBalanceResponse = new GetAccountBalanceResponse();

        Error error = new Error();
        error.setCode(errorCode);
        error.setDescription(errorDescription);

        getAccountBalanceResponse.getErrors().add(error);
        getAccountBalanceResponse.setStatus(status);
        return getAccountBalanceResponse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAccountBalanceResponse that = (GetAccountBalanceResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(errors, that.errors) && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, errors, payload);
    }
}
