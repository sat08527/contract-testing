package com.example.ProducerDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

@RestController
public class CustomerRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public Collection<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
