package com.example.clientdemo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerClientConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CustomerClient customerClient( RestTemplate restTemplate){
        return new CustomerClient(restTemplate);
    }
}
