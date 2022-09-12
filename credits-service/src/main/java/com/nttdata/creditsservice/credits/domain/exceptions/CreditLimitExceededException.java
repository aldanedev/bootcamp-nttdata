package com.nttdata.creditsservice.credits.domain.exceptions;

public class CreditLimitExceededException extends RuntimeException {
  public CreditLimitExceededException(String message) {
    super(message);
  }

}
