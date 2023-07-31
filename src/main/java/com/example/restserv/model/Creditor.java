package com.example.restserv.model;

import java.util.Objects;

public class Creditor {

    private String name;
    private Account account;

    public Creditor(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Creditor{");
        sb.append("name='").append(name).append('\'');
        sb.append(", account=").append(account);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creditor creditor = (Creditor) o;
        return Objects.equals(name, creditor.name) && Objects.equals(account, creditor.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, account);
    }
}
