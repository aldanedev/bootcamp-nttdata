package com.nttdata.creditsservice.credits.infrastructure.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.creditsservice.credits.infrastructure.mapping.CreditCardDocument;

import reactor.core.publisher.Mono;

public interface CreditCardStore extends ReactiveMongoRepository<CreditCardDocument, String> {
  Mono<CreditCardDocument> findByNumber(String number);
}
