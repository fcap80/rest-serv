package com.example.restserv.requests;

import com.example.restserv.model.Creditor;

import java.time.LocalDate;

public class ExecuteWireTransferRequest {

    private String description;
    private String currency;
    private Double amount;
    private LocalDate executionDate;
    private Creditor creditor;

    public ExecuteWireTransferRequest(String description, String currency, Double amount, LocalDate executionDate, Creditor creditor) {
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.executionDate = executionDate;
        this.creditor = creditor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExecuteWireTransferRequest{");
        sb.append(", description='").append(description).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", executionDate=").append(executionDate);
        sb.append(", creditor=").append(creditor);
        sb.append('}');
        return sb.toString();
    }
}
