package com.example.restserv.services;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.requests.GetAccountOrBalanceRequest;
import com.example.restserv.responses.balance.GetAccountBalanceResponse;
import com.example.restserv.responses.transactions.GetTransactionsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;

import static org.springframework.http.HttpMethod.GET;

@Service
public class GetAccountBalanceRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAccountBalanceRestService.class);

    private final RestServiceHelper restServiceHelper;
    private final RestTemplate restTemplate;

    public GetAccountBalanceRestService(RestServiceHelper restServiceHelper,
                                        RestTemplate restTemplate) {
        this.restServiceHelper = restServiceHelper;
        this.restTemplate = restTemplate;
    }

    private GetAccountBalanceResponse getAccountBalance(@Nonnull GetAccountOrBalanceRequest getAccountOrBalanceRequest)
            throws RestApiException, JsonProcessingException {
        String url = restServiceHelper.composeBaseUrlForAccounts();
        ResponseEntity<GetAccountBalanceResponse> responseEntity;
        try {
            HttpEntity<Void> requestEntity = restServiceHelper.createBaseRequestEntity();
            responseEntity = restTemplate.exchange(url + "/{accountId}/balance", GET, requestEntity, GetAccountBalanceResponse.class, getAccountOrBalanceRequest.getAccountId());
            return responseEntity.getBody();
        } catch (RestClientResponseException e) {
            LOGGER.error("Error", e);
            return e.getResponseBodyAs(GetAccountBalanceResponse.class);
        } catch (Exception e) {
            LOGGER.error("Error", e);
            throw e;
        }
    }

    public GetAccountBalanceResponse performGetAccountBalance(long accountId) {
        GetAccountOrBalanceRequest getAccountOrBalanceRequest = new GetAccountOrBalanceRequest(accountId);
        try {
            GetAccountBalanceResponse accountBalance = getAccountBalance(getAccountOrBalanceRequest);
            LOGGER.info("Account retrieved: {}", accountBalance);
            return accountBalance;
        } catch (JsonProcessingException e) {
            return GetAccountBalanceResponse.failWithOneError(
                    "KO", "ERR-1", "Cannot process JSON: " + e.getMessage());
        } catch (Exception e) {
            return GetAccountBalanceResponse.failWithOneError(
                    "KO", "ERR-1", "Unexpected Exception: " + e.getMessage());
        }
    }

    //@Scheduled(fixedDelay = 30_000L)
    public void sendGetAccountBalance() {
        performGetAccountBalance(14537780L);
    }
}
