package com.example.restserv.controllers;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.model.Transaction;
import com.example.restserv.model.mappers.TransactionMapper;
import com.example.restserv.requests.ExecuteWireTransferRequest;
import com.example.restserv.responses.balance.GetAccountBalanceResponse;
import com.example.restserv.responses.moneytransfer.ExecuteMoneyTransferResponse;
import com.example.restserv.responses.transactions.GetTransactionsResponse;
import com.example.restserv.services.ExecuteMoneyTransferRestService;
import com.example.restserv.services.GetAccountBalanceRestService;
import com.example.restserv.services.GetTransactionsRestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final GetAccountBalanceRestService getAccountBalanceRestService;
    private final GetTransactionsRestService getTransactionsRestService;
    private final ExecuteMoneyTransferRestService executeMoneyTransferRestService;

    private final TransactionMapper transactionMapper;

    public AccountController(GetAccountBalanceRestService getAccountBalanceRestService,
                             GetTransactionsRestService getTransactionsRestService,
                             ExecuteMoneyTransferRestService executeMoneyTransferRestService,
                             TransactionMapper transactionMapper) {
        this.getAccountBalanceRestService = getAccountBalanceRestService;
        this.getTransactionsRestService = getTransactionsRestService;
        this.executeMoneyTransferRestService = executeMoneyTransferRestService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/{accountId}/balance")
    public GetAccountBalanceResponse getBalance(@PathVariable("accountId") Long accountId) {
        return getAccountBalanceRestService.getAccountBalanceRequest(accountId);
    }

    @GetMapping("/{accountId}/transactions")
    public GetTransactionsResponse getTransactions(@PathVariable("accountId") Long accountId,
                                                   @RequestParam(name = "fromAccountingDate") LocalDate fromAccountingDate,
                                                   @RequestParam(name = "toAccountingDate") LocalDate toAccountingDate) {
        GetTransactionsResponse transactionsRequest = getTransactionsRestService.getTransactionsRequest(accountId, fromAccountingDate, toAccountingDate);
        storeRetrievedTransactions(transactionsRequest.getPayload().getList());
        return transactionsRequest;
    }

    private void storeRetrievedTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transactionMapper.update(transaction) == 0) {
                transactionMapper.insert(transaction);
            }
        }
    }

    @PostMapping("/{accountId}/payments/money-transfer")
    public ExecuteMoneyTransferResponse executeMoneyTransfer(@PathVariable("accountId") Long accountId,
                                                             @RequestBody ExecuteWireTransferRequest executeWireTransferRequest) throws RestApiException, JsonProcessingException {
        return executeMoneyTransferRestService.executeWireTransfer(
                accountId, executeWireTransferRequest);
    }

}
