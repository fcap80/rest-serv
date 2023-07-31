package com.example.restserv.responses.balance;

import java.time.LocalDate;

public class GetAccountBalancePayloadResponse {


    private LocalDate date;
    private Double balance;
    private Double availableBalance;
    private String currency;

    public GetAccountBalancePayloadResponse() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAccountBalancePayloadResponse{");
        sb.append("date=").append(date);
        sb.append(", balance=").append(balance);
        sb.append(", availableBalance=").append(availableBalance);
        sb.append(", currency='").append(currency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
