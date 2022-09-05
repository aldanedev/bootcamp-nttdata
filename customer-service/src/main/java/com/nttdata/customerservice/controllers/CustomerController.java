package com.nttdata.customerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.customerservice.controllers.request.CustomerRequest;
import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.application.create.CustomerCreator;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

  @Autowired
  private CustomerCreator customerCreator;

  @PostMapping
  public Mono<ResponseEntity<CustomerResponse>> create(@RequestBody CustomerRequest customerRequest) {
    return customerCreator.create(customerRequest.getName())
        .flatMap(customerResponse -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(customerResponse)));
  }
}
