package com.nttdata.creditsservice.controllers.request;

import lombok.Data;

@Data
public class CreditCardMovementRequest {
  private String description;
  private Double amount;
  private String creditCardId;
  private String type;
}
