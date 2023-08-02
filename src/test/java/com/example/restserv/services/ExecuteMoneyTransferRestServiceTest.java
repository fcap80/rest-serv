package com.example.restserv.services;

import com.example.restserv.model.Error;
import com.example.restserv.responses.moneytransfer.ExecuteMoneyTransferResponse;
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
import java.util.ArrayList;
import java.util.List;

import static com.example.restserv.utils.SharedConstants.RestParams.ACCOUNTS_URI;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExecuteMoneyTransferRestServiceTest {

    private final long TEST_ACCOUNT_ID = 14537780;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExecuteMoneyTransferRestService executeMoneyTransferRestService;

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

        ExecuteMoneyTransferResponse moneyTransferResponse = prepareMockResponse();

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/" + ACCOUNTS_URI + "/" + TEST_ACCOUNT_ID + "/payments/money-transfers")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(moneyTransferResponse))
                );

        ExecuteMoneyTransferResponse transactionsResponse = executeMoneyTransferRestService.performExecuteWireTransfer(
                TEST_ACCOUNT_ID,
                "desc-test",
                "EUR",
                100.0,
                LocalDate.now(),
                "Creditor-test",
                "IBAN-test");
        mockServer.verify();
        Assertions.assertEquals(moneyTransferResponse, transactionsResponse);
    }

    private static ExecuteMoneyTransferResponse prepareMockResponse() {
        ExecuteMoneyTransferResponse executeMoneyTransferResponse = new ExecuteMoneyTransferResponse();
        executeMoneyTransferResponse.setStatus("KO");

        List<Error> errors = new ArrayList<>();
        Error error = new Error();
        error.setCode("API000");
        error.setDescription("Errore tecnico La condizione BP049 non è prevista per il conto id 14537780");
        errors.add(error);
        executeMoneyTransferResponse.setErrors(errors);
        return executeMoneyTransferResponse;
    }
}
