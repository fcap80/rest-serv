package com.example.restserv.responses.transactions;

import com.example.restserv.model.Transaction;

import java.util.List;
import java.util.Objects;

public class GetTransactionsPayloadResponse {

    private List<Transaction> list;


    public GetTransactionsPayloadResponse() {
    }

    public List<Transaction> getList() {
        return list;
    }

    public void setList(List<Transaction> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetTransactionsPayloadResponse{");
        sb.append("list=").append(list);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTransactionsPayloadResponse that = (GetTransactionsPayloadResponse) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
