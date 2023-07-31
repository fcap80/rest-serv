package com.example.restserv.model;

import java.util.Objects;

public class TransactionType {

    private String enumeration;
    private String value;

    public String getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionType{");
        sb.append("enumeration='").append(enumeration).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(enumeration, that.enumeration) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enumeration, value);
    }
}
