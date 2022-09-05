package com.nttdata.customerservice.customers.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.customerservice.customers.domain.Customer;
import com.nttdata.customerservice.customers.domain.CustomerRepository;
import com.nttdata.customerservice.customers.infrastructure.mapping.CustomerDocument;

import reactor.core.publisher.Mono;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

  @Autowired
  public CustomerStore customerStore;

  public Mono<Customer> save(Customer customer) {
    return customerStore.save(CustomerDocument.builder()
        .id(customer.getId())
        .name(customer.getName())
        .build())
        .flatMap(customerDocument -> Mono.just(
            Customer.builder()
                .id(customerDocument.getId())
                .name(customerDocument.getName())
                .build()));

  }
}
