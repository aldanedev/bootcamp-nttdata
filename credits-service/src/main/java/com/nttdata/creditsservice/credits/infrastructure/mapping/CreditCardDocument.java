package com.nttdata.creditsservice.credits.infrastructure.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "credit_cards")
@Data
@Builder
public class CreditCardDocument {
  @Id
  private String id;
  private String description;
  private String number;
  private Double limitCredit;
  private String customerId;
}
