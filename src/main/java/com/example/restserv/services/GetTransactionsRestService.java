package com.example.restserv.services;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.model.Transaction;
import com.example.restserv.model.mappers.TransactionMapper;
import com.example.restserv.requests.GetAccountTransactionsRequest;
import com.example.restserv.responses.transactions.GetTransactionsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;
import java.time.LocalDate;

import static org.springframework.http.HttpMethod.GET;

@Service
public class GetTransactionsRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetTransactionsRestService.class);

    private final RestServiceHelper restServiceHelper;
    private final RestTemplate restTemplate;

    private final TransactionMapper transactionMapper;

    public GetTransactionsRestService(RestServiceHelper restServiceHelper,
                                      RestTemplate restTemplate,
                                      TransactionMapper transactionMapper) {
        this.restServiceHelper = restServiceHelper;
        this.restTemplate = restTemplate;
        this.transactionMapper = transactionMapper;
    }

    private GetTransactionsResponse getTransactions(@Nonnull GetAccountTransactionsRequest getAccountTransactionsRequest)
            throws RestApiException, JsonProcessingException {
        String url = restServiceHelper.composeBaseUrlForAccounts();
        ResponseEntity<GetTransactionsResponse> responseEntity;
        try {
            HttpEntity<Void> requestEntity = restServiceHelper.createBaseRequestEntity();
            String composedUrl = url + "/{accountId}/transactions";

            String urlTemplate = UriComponentsBuilder.fromHttpUrl(composedUrl)
                    .queryParam("fromAccountingDate", getAccountTransactionsRequest.getFromAccountingDate())
                    .queryParam("toAccountingDate", getAccountTransactionsRequest.getToAccountingDate())
                    .encode()
                    .toUriString();

            responseEntity = restTemplate.exchange(urlTemplate, GET, requestEntity, GetTransactionsResponse.class, getAccountTransactionsRequest.getAccountId());
            return responseEntity.getBody();
        } catch (RestClientResponseException e) {
            LOGGER.error("Error", e);
            return e.getResponseBodyAs(GetTransactionsResponse.class);
        } catch (Exception e) {
            LOGGER.error("Unexpected Exception", e);
            throw e;
        }
    }

    public GetTransactionsResponse performGetTransactions(long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        GetAccountTransactionsRequest getAccountTransactionsRequest = new GetAccountTransactionsRequest(
                accountId, fromAccountingDate, toAccountingDate);
        try {
            GetTransactionsResponse transactions = getTransactions(getAccountTransactionsRequest);
            LOGGER.info("Transactions retrieved: {}", transactions);
            return transactions;
        } catch (JsonProcessingException e) {
            return GetTransactionsResponse.failWithOneError(
                    "KO", "ERR-1", "Cannot process JSON: " + e.getMessage());
        } catch (Exception e) {
            return GetTransactionsResponse.failWithOneError(
                    "KO", "ERR-1", "Unexpected Exception: " + e.getMessage());
        }
    }

    //@Scheduled(fixedDelay = 30_000L)
    public void sendGetTransactions() {
        GetTransactionsResponse transactionsRequest = performGetTransactions(14537780L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10));

        for (Transaction transaction : transactionsRequest.getPayload().getList()) {
            if (transactionMapper.update(transaction) == 0) {
                transactionMapper.insert(transaction);
            }
        }
    }
}
