package com.example.restserv.services;

import com.example.restserv.exceptions.RestApiException;
import com.example.restserv.requests.GetAccountOrBalanceRequest;
import com.example.restserv.responses.account.GetAccountResponse;
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
public class GetAccountRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAccountRestService.class);

    private final RestServiceHelper restServiceHelper;
    private final RestTemplate restTemplate;

    public GetAccountRestService(RestServiceHelper restServiceHelper,
                                 RestTemplate restTemplate) {
        this.restServiceHelper = restServiceHelper;
        this.restTemplate = restTemplate;
    }

    private GetAccountResponse getAccount(@Nonnull GetAccountOrBalanceRequest getAccountOrBalanceRequest)
            throws RestApiException, JsonProcessingException {
        String url = restServiceHelper.composeBaseUrlForAccounts();
        ResponseEntity<GetAccountResponse> responseEntity;
        try {
            HttpEntity<Void> requestEntity = restServiceHelper.createBaseRequestEntity();
            responseEntity = restTemplate.exchange(url + "/{accountId}", GET, requestEntity, GetAccountResponse.class, getAccountOrBalanceRequest.getAccountId());
            return responseEntity.getBody();
        } catch (RestClientResponseException e) {
            LOGGER.error("Error", e);
            return e.getResponseBodyAs(GetAccountResponse.class);
        } catch (Exception e) {
            LOGGER.error("Really bad Error happened", e);
            throw new RestApiException();
        }
    }

    public void getAccountRequest(long accountId) {
        GetAccountOrBalanceRequest getAccountOrBalanceRequest = new GetAccountOrBalanceRequest(accountId);
        try {
            GetAccountResponse balance = getAccount(getAccountOrBalanceRequest);
            LOGGER.info("Account retrieved: {}", balance);
        } catch (RestApiException e) {
            LOGGER.error("Error in invoking API: ", e);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //@Scheduled(fixedDelay = 30_000L)
    public void sendGetAccount() {
        getAccountRequest(14537780L);
    }
}
