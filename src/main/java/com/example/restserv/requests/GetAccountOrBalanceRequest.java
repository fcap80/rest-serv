package com.example.restserv.requests;

public class GetAccountOrBalanceRequest {

    private long accountId;

    public GetAccountOrBalanceRequest(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAccountOrBalanceRequest{");
        sb.append("accountId=").append(accountId);
        sb.append('}');
        return sb.toString();
    }
}
