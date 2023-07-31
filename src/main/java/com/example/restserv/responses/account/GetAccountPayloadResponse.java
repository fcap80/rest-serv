package com.example.restserv.responses.account;

public class GetAccountPayloadResponse {


    private String accountId;
    private String iban;
    private String abiCode;
    private String cabCode;
    private String countryCode;
    private String internationalCin;
    private String nationalCin;
    private String account;
    private String alias;
    private String productName;
    private String holderName;
    private String activatedDate;
    private String currency;

    public GetAccountPayloadResponse() {
    }

    public GetAccountPayloadResponse(String accountId, String iban, String abiCode, String cabCode, String countryCode, String internationalCin, String nationalCin, String account, String alias, String productName, String holderName, String activatedDate, String currency) {
        this.accountId = accountId;
        this.iban = iban;
        this.abiCode = abiCode;
        this.cabCode = cabCode;
        this.countryCode = countryCode;
        this.internationalCin = internationalCin;
        this.nationalCin = nationalCin;
        this.account = account;
        this.alias = alias;
        this.productName = productName;
        this.holderName = holderName;
        this.activatedDate = activatedDate;
        this.currency = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAbiCode() {
        return abiCode;
    }

    public void setAbiCode(String abiCode) {
        this.abiCode = abiCode;
    }

    public String getCabCode() {
        return cabCode;
    }

    public void setCabCode(String cabCode) {
        this.cabCode = cabCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getInternationalCin() {
        return internationalCin;
    }

    public void setInternationalCin(String internationalCin) {
        this.internationalCin = internationalCin;
    }

    public String getNationalCin() {
        return nationalCin;
    }

    public void setNationalCin(String nationalCin) {
        this.nationalCin = nationalCin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(String activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payload{");
        sb.append("accountId='").append(accountId).append('\'');
        sb.append(", iban='").append(iban).append('\'');
        sb.append(", abiCode='").append(abiCode).append('\'');
        sb.append(", cabCode='").append(cabCode).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", internationalCin='").append(internationalCin).append('\'');
        sb.append(", nationalCin='").append(nationalCin).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", holderName='").append(holderName).append('\'');
        sb.append(", activatedDate='").append(activatedDate).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
