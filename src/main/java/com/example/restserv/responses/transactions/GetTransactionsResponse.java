package com.example.restserv.responses.transactions;

import com.example.restserv.model.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionsResponse {

    private String status;
    private List<Error> errors = new ArrayList<>();
    private GetTransactionsPayloadResponse payload;

    public static GetTransactionsResponse failWithOneError(String status, String errorCode, String errorDescription) {
        GetTransactionsResponse getTransactionsResponse = new GetTransactionsResponse();

        Error error = new Error();
        error.setCode(errorCode);
        error.setDescription(errorDescription);

        getTransactionsResponse.getErrors().add(error);
        getTransactionsResponse.setStatus(status);
        return getTransactionsResponse;
    }

    public GetTransactionsResponse() {
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

    public GetTransactionsPayloadResponse getPayload() {
        return payload;
    }

    public void setPayload(GetTransactionsPayloadResponse payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetTransactionsResponse{");
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
        GetTransactionsResponse that = (GetTransactionsResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(errors, that.errors) && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, errors, payload);
    }
}
