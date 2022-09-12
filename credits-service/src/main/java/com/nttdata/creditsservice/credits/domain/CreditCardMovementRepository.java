package com.nttdata.creditsservice.credits.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardMovementRepository {
  public Mono<CreditCardMovement> save(CreditCardMovement creditCard);

  public Flux<CreditCardMovement> findCreditCardIdBy(String id);
}
