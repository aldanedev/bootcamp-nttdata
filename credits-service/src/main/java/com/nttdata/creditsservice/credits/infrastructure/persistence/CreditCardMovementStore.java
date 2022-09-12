package com.nttdata.creditsservice.credits.infrastructure.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.creditsservice.credits.infrastructure.mapping.CreditCardMovementDocument;

import reactor.core.publisher.Flux;

public interface CreditCardMovementStore extends ReactiveMongoRepository<CreditCardMovementDocument, String> {
  public Flux<CreditCardMovementDocument> findByCreditCardId(String id);

}
