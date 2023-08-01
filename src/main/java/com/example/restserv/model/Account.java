package com.example.restserv.model;

import java.util.Objects;

public class Account {

    public Account() {
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("accountCode='").append(accountCode).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountCode, account.accountCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountCode);
    }
}
