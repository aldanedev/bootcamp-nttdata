package com.nttdata.customerservice.customers.application.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.customerservice.customers.application.CustomerResponse;
import com.nttdata.customerservice.customers.domain.Customer;
import com.nttdata.customerservice.customers.domain.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerCreator {

  private final CustomerRepository repository;

  public Mono<CustomerResponse> create(String name) {
    Customer customer = Customer.create(name);

    return repository.save(customer)
        .flatMap(customerSaved -> Mono.just(new CustomerResponse(customerSaved.getId(), customerSaved.getName())));
  }
}
