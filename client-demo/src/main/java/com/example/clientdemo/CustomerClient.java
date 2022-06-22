package com.example.clientdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerClient {

    private RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Collection<Customer> getAllCustomers(){
        ParameterizedTypeReference<Collection<Customer>> ptr=new ParameterizedTypeReference<Collection<Customer>>() {
        };
        ResponseEntity<Collection<Customer>> responseEntity=
                this.restTemplate.exchange("http://localhost:8081/customers", HttpMethod.GET,null ,ptr);

        return responseEntity.getBody();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer{
    private Long id;
    private String name;
}