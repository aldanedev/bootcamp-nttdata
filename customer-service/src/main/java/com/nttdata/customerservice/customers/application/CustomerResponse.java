package com.nttdata.customerservice.customers.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
  private String id;
  private String name;
}
