package com.nttdata.customerservice.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Customer {
  private String id;
  private String name;

  public Customer(String name) {
    this.name = name;
  }

  public static Customer create(String name) {
    return new Customer(name);
  }
}
