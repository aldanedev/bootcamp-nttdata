package com.nttdata.creditsservice.credits.domain.exceptions;

public class CreditCardAlreadyExists extends RuntimeException {
  public CreditCardAlreadyExists(String message) {
    super(message);
  }
}
