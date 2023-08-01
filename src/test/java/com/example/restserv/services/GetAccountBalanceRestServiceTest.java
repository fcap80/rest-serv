package com.example.restserv.services;

import com.example.restserv.responses.balance.GetAccountBalancePayloadResponse;
import com.example.restserv.responses.balance.GetAccountBalanceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static com.example.restserv.utils.SharedConstants.RestParams.ACCOUNTS_URI;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GetAccountBalanceRestServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetAccountBalanceRestService getAccountBalanceRestService;

    private MockRestServiceServer mockServer;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer
                .bindTo(restTemplate)
                .bufferContent()
                .build();
    }

    @Test
    public void test() throws JsonProcessingException, URISyntaxException {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        GetAccountBalanceResponse getAccountBalanceResponse = prepareMockResponse();

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/" + ACCOUNTS_URI + "/1/balance")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(getAccountBalanceResponse))
                );

        GetAccountBalanceResponse accountBalanceRequest = getAccountBalanceRestService.getAccountBalanceRequest(1);
        mockServer.verify();
        Assertions.assertEquals(getAccountBalanceResponse, accountBalanceRequest);

    }

    private static GetAccountBalanceResponse prepareMockResponse() {
        GetAccountBalanceResponse getAccountBalanceResponse = new GetAccountBalanceResponse();
        getAccountBalanceResponse.setStatus("OK");
        GetAccountBalancePayloadResponse getAccountBalancePayloadResponse = new GetAccountBalancePayloadResponse();
        getAccountBalancePayloadResponse.setBalance(100.0);
        getAccountBalancePayloadResponse.setCurrency("EUR");
        getAccountBalancePayloadResponse.setAvailableBalance(100.0);
        getAccountBalancePayloadResponse.setDate(LocalDate.now());
        getAccountBalanceResponse.setPayload(getAccountBalancePayloadResponse);
        return getAccountBalanceResponse;
    }

}
