package com.nttdata.creditsservice.credits.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardRepository {
  public Mono<CreditCard> save(CreditCard creditCard);

  public Mono<CreditCard> findById(String id);

  Mono<CreditCard> findByNumber(String number);

  public Flux<CreditCard> findAll();

}