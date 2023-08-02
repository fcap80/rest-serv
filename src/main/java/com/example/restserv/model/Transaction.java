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
    private @Nullable String typeEnumeration;
    private @Nullable String typeValue;
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

    @Nullable
    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(@Nullable LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    @Nullable
    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(@Nullable LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    @Nullable
    public TransactionType getType() {
        return type;
    }

    public void setTypeEnumeration(@Nullable String typeEnumeration) {
        this.typeEnumeration = typeEnumeration;
    }

    public void setTypeValue(@Nullable String typeValue) {
        this.typeValue = typeValue;
    }

    public void setType(@Nullable TransactionType type) {
        this.type = type;
    }


    public String getTypeEnumeration() {
        if (getType() != null) {
            return getType().getEnumeration();
        } else return typeEnumeration;
    }

    public String getTypeValue() {
        if (getType() != null) {
            return getType().getValue();
        } else return typeValue;
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

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String sb = "Transaction{" + "transactionId='" + transactionId + '\'' +
                ", operationId='" + operationId + '\'' +
                ", accountingDate='" + accountingDate + '\'' +
                ", valueDate=" + valueDate +
                ", type=" + type +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                '}';
        return sb;
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
