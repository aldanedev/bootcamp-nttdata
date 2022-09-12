package com.nttdata.creditsservice.controllers.request;

import lombok.Data;

@Data
public class CreditCardRequest {
  private String description;
  private String number;
  private Double limitCredit;
  private String customerId;
}
