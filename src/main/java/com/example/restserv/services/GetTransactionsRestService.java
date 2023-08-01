package com.example.restserv.services;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.model.Transaction;
import com.example.restserv.model.mappers.TransactionMapperBase;
import com.example.restserv.requests.GetAccountTransactionsRequest;
import com.example.restserv.responses.transactions.GetTransactionsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    private final TransactionMapperBase transactionMapperBase;

    public GetTransactionsRestService(RestServiceHelper restServiceHelper,
                                      RestTemplate restTemplate,
                                      TransactionMapperBase transactionMapperBase) {
        this.restServiceHelper = restServiceHelper;
        this.restTemplate = restTemplate;
        this.transactionMapperBase = transactionMapperBase;
    }

    protected GetTransactionsResponse getTransactions(@Nonnull GetAccountTransactionsRequest getAccountTransactionsRequest)
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
            throw new RestApiException(e);
        } catch (Exception e) {
            LOGGER.error("Error", e);
            throw e;
        }
    }

    public GetTransactionsResponse getTransactionsRequest(long accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        GetAccountTransactionsRequest getAccountTransactionsRequest = new GetAccountTransactionsRequest(
                accountId, fromAccountingDate, toAccountingDate);
        try {
            GetTransactionsResponse transactions = getTransactions(getAccountTransactionsRequest);
            LOGGER.info("Transactions retrieved: {}", transactions);
            return transactions;
        } catch (RestApiException e) {
            LOGGER.error("Error in invoking API: ", e);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedDelay = 30_000L)
    public void sendGetTransactions() {
        GetTransactionsResponse transactionsRequest = getTransactionsRequest(14537780L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10));

        for (Transaction transaction : transactionsRequest.getPayload().getList()) {
            transactionMapperBase.insert(transaction);
        }


    }
}
