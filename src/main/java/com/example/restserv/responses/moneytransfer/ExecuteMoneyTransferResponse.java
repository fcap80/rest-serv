package com.example.restserv.responses.moneytransfer;

import com.example.restserv.model.Error;

import java.util.List;

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
}
