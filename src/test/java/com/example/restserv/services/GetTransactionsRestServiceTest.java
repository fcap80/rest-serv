package com.example.restserv.services;

import com.example.restserv.model.Transaction;
import com.example.restserv.model.TransactionType;
import com.example.restserv.responses.balance.GetAccountBalancePayloadResponse;
import com.example.restserv.responses.balance.GetAccountBalanceResponse;
import com.example.restserv.responses.transactions.GetTransactionsPayloadResponse;
import com.example.restserv.responses.transactions.GetTransactionsResponse;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.restserv.utils.SharedConstants.RestParams.ACCOUNTS_URI;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GetTransactionsRestServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetTransactionsRestService getTransactionsRestService;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

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

        GetTransactionsResponse getTransactionsResponse = new GetTransactionsResponse();
        getTransactionsResponse.setStatus("OK");
        GetTransactionsPayloadResponse getTransactionsPayloadResponse = new GetTransactionsPayloadResponse();
        List<Transaction> list = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setCurrency("EUR");
        transaction.setAmount(100.0);
        TransactionType transactionType = new TransactionType();
        transactionType.setValue("tp-value");
        transactionType.setEnumeration("tp-enum");
        transaction.setType(transactionType);
        transaction.setDescription("desc");
        transaction.setAccountingDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        list.add(transaction);
        getTransactionsPayloadResponse.setList(list);
        getTransactionsResponse.setPayload(getTransactionsPayloadResponse);

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/" + ACCOUNTS_URI + "/1/transactions" +
                                "?fromAccountingDate=" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) +
                                "&toAccountingDate=" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(getTransactionsResponse))
                );

        GetTransactionsResponse transactionsResponse = getTransactionsRestService.getTransactionsRequest(
                1, LocalDate.now(), LocalDate.now());
        mockServer.verify();
        Assertions.assertEquals(getTransactionsResponse, transactionsResponse);

    }

}
