package com.example.restserv.responses.moneytransfer;

import com.example.restserv.model.Error;

import java.util.List;
import java.util.Objects;

public class ExecuteMoneyTransferResponse {

    private String status;
    private List<Error> errors;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExecuteMoneyTransferResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecuteMoneyTransferResponse that = (ExecuteMoneyTransferResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, errors);
    }
}
