package com.nttdata.customerservice.customers.domain;

import reactor.core.publisher.Mono;

public interface CustomerRepository {
  public Mono<Customer> save(Customer customer);
}
