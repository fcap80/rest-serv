package com.example.restserv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private String transactionId;
    private String operationId;
    private @Nullable LocalDate accountingDate;
    private @Nullable LocalDate valueDate;
    private @Nullable TransactionType type;
    private double amount;
    private String currency;
    private @Nullable String description;

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("transactionId='").append(transactionId).append('\'');
        sb.append(", operationId='").append(operationId).append('\'');
        sb.append(", accountingDate='").append(accountingDate).append('\'');
        sb.append(", valueDate=").append(valueDate);
        sb.append(", type=").append(type);
        sb.append(", amount=").append(amount);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(transactionId, that.transactionId) && Objects.equals(operationId, that.operationId) && Objects.equals(accountingDate, that.accountingDate) && Objects.equals(valueDate, that.valueDate) && Objects.equals(type, that.type) && Objects.equals(currency, that.currency) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, operationId, accountingDate, valueDate, type, amount, currency, description);
    }
}
