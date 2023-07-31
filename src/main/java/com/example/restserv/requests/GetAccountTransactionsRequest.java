package com.example.restserv.requests;

import java.time.LocalDate;

public class GetAccountTransactionsRequest {

    private long accountId;
    private LocalDate fromAccountingDate;
    private LocalDate toAccountingDate;

    public GetAccountTransactionsRequest(long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        this.accountId = accountId;
        this.fromAccountingDate = fromAccountingDate;
        this.toAccountingDate = toAccountingDate;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public LocalDate getFromAccountingDate() {
        return fromAccountingDate;
    }

    public void setFromAccountingDate(LocalDate fromAccountingDate) {
        this.fromAccountingDate = fromAccountingDate;
    }

    public LocalDate getToAccountingDate() {
        return toAccountingDate;
    }

    public void setToAccountingDate(LocalDate toAccountingDate) {
        this.toAccountingDate = toAccountingDate;
    }
}
