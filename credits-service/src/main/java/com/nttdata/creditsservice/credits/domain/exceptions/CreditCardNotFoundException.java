package com.nttdata.creditsservice.credits.domain.exceptions;

public class CreditCardNotFoundException extends RuntimeException {

  public CreditCardNotFoundException(String id) {
    super("Credit card not found with id: " + id);
  }

}
