package com.nttdata.creditsservice.credits.application;

import com.nttdata.creditsservice.credits.domain.CreditCard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardResponse {
  private String id;
  private String description;
  private String number;
  private Double limitCredit;
  private String customerId;

  public static CreditCardResponse fromCreditCard(CreditCard creditCard) {
    return new CreditCardResponse(creditCard.getId(), creditCard.getDescription(), creditCard.getNumber(),
        creditCard.getLimitCredit(), creditCard.getCustomerId());
  }
}
