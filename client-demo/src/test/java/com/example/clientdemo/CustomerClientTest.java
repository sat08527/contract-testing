package com.example.clientdemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientDemoApplication.class)
//@AutoConfigureWireMock(port=8081)
@AutoConfigureStubRunner(ids = "com.example:ProducerDemo:+:8081",workOffline = true)
public class CustomerClientTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CustomerClient customerClient;


    @Test
    public void clientShouldReturnAllCustomers() throws JsonProcessingException {
      /*  WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                        .withStatus(200)
                                        .withBody(jsonForCustomer(new Customer(1L,"Jane"),
                                                new Customer(2L,"Bob")))
                        ));*/
        Collection<Customer> allCustomers = this.customerClient.getAllCustomers();
        BDDAssertions.then(allCustomers).size().isEqualTo(2);
        BDDAssertions.then(allCustomers.iterator().next().getId()).isEqualTo(1L);
        BDDAssertions.then(allCustomers.iterator().next().getName()).isEqualTo("Jane");
    }

    private String jsonForCustomer(Customer ...customers) throws JsonProcessingException {
        List<Customer> customerList= Arrays.asList(customers);
        return mapper.writeValueAsString(customerList);
    }
}
