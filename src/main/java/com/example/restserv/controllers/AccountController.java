package com.example.restserv.controllers;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.model.Transaction;
import com.example.restserv.requests.ExecuteWireTransferRequest;
import com.example.restserv.services.ExecuteMoneyTransferRestService;
import com.example.restserv.services.GetAccountBalanceRestService;
import com.example.restserv.services.GetTransactionsRestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/account")
public class AccountController {

    private final GetAccountBalanceRestService getAccountBalanceRestService;
    private final GetTransactionsRestService getTransactionsRestService;
    private final ExecuteMoneyTransferRestService executeMoneyTransferRestService;

    public AccountController(GetAccountBalanceRestService getAccountBalanceRestService,
                             GetTransactionsRestService getTransactionsRestService,
                             ExecuteMoneyTransferRestService executeMoneyTransferRestService) {
        this.getAccountBalanceRestService = getAccountBalanceRestService;
        this.getTransactionsRestService = getTransactionsRestService;
        this.executeMoneyTransferRestService = executeMoneyTransferRestService;
    }

    @GetMapping("/{accountId}/balance")
    public Double getBalance(@PathVariable("accountId") Long accountId) {
        return getAccountBalanceRestService.getAccountBalanceRequest(accountId).getPayload().getBalance();
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable("accountId") Long accountId,
                                             @RequestParam(name = "fromAccountingDate") LocalDate fromAccountingDate,
                                             @RequestParam(name = "toAccountingDate") LocalDate toAccountingDate) {
        return getTransactionsRestService.getTransactionsRequest(accountId, fromAccountingDate, toAccountingDate).getPayload().getList();
    }

    @PostMapping("/{accountId}/payments/money-transfer")
    public void getTransactions(@PathVariable("accountId") Long accountId,
                                @RequestBody ExecuteWireTransferRequest executeWireTransferRequest) throws RestApiException, JsonProcessingException {
        executeMoneyTransferRestService.executeWireTransfer(
                accountId, executeWireTransferRequest);
    }

}
