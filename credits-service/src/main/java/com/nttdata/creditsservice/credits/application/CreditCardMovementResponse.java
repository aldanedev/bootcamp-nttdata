package com.nttdata.creditsservice.credits.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardMovementResponse {
  private String id;
  private String description;
  private Double amount;
  private String creditCardId;
  private String type;
}
