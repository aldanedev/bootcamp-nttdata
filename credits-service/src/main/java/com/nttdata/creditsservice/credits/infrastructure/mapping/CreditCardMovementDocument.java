package com.nttdata.creditsservice.credits.infrastructure.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "credit_card_movements")
@Data
@Builder
public class CreditCardMovementDocument {
  @Id
  private String id;
  private String description;
  private Double amount;
  private String creditCardId;
  private String type;
}
