package com.example.restserv.services;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.model.Account;
import com.example.restserv.model.Creditor;
import com.example.restserv.requests.ExecuteWireTransferRequest;
import com.example.restserv.responses.moneytransfer.ExecuteMoneyTransferResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ExecuteMoneyTransferRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteMoneyTransferRestService.class);

    private final RestServiceHelper restServiceHelper;
    private final RestTemplate restTemplate;

    public ExecuteMoneyTransferRestService(RestServiceHelper restServiceHelper,
                                           RestTemplate restTemplate) {
        this.restServiceHelper = restServiceHelper;
        this.restTemplate = restTemplate;
    }

    public ExecuteMoneyTransferResponse executeWireTransfer(long accountId, ExecuteWireTransferRequest executeWireTransferRequest)
            throws RestApiException, JsonProcessingException {
        String url = restServiceHelper.composeBaseUrlForAccounts();
        ResponseEntity<ExecuteMoneyTransferResponse> responseEntity;
        try {
            HttpEntity<String> requestEntity = restServiceHelper.createRequestEntityForMoneyTransfer(executeWireTransferRequest);
            responseEntity = restTemplate.exchange(url + "/{accountId}/payments/money-transfers",
                    HttpMethod.POST, requestEntity, ExecuteMoneyTransferResponse.class, accountId);
            return responseEntity.getBody();
        } catch (RestClientResponseException e) {
            LOGGER.error("Error", e);
            return e.getResponseBodyAs(ExecuteMoneyTransferResponse.class);
        } catch (Exception e) {
            LOGGER.error("Really bad Error happened", e);
            throw new RestApiException();
        }
    }

    public ExecuteMoneyTransferResponse executeWireTransfer(long accountId,
                                                            String description,
                                                            String currency,
                                                            Double amount,
                                                            LocalDate executionDate,
                                                            String creditorName,
                                                            String creditorAccountIban) {
        Account account = new Account(creditorAccountIban);
        Creditor creditor = new Creditor(creditorName, account);
        ExecuteWireTransferRequest executeWireTransferRequest = new ExecuteWireTransferRequest(
                description, currency, amount, executionDate, creditor);
        try {
            ExecuteMoneyTransferResponse executeMoneyTransferResponse = executeWireTransfer(accountId, executeWireTransferRequest);
            LOGGER.info("Money Transfer Executed: {}", executeMoneyTransferResponse);
            return executeMoneyTransferResponse;
        } catch (RestApiException e) {
            LOGGER.error("Error in invoking API: ", e);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //@Scheduled(fixedDelay = 30_000L)
    public void sendExecuteAccountBalance() {
        executeWireTransfer(14537780L,
                "description",
                "EUR",
                99.0,
                LocalDate.now(),
                "LUCA TERRIBILE",
                "IT40L0326822311052923800661");
    }
}
