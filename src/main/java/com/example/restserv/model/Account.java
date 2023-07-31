package com.example.restserv.model;

public class Account {

    private String accountCode;

    public Account(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
