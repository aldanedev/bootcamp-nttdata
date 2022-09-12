package com.nttdata.creditsservice.credits.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.creditsservice.credits.domain.CreditCard;
import com.nttdata.creditsservice.credits.domain.CreditCardRepository;
import com.nttdata.creditsservice.credits.infrastructure.mapping.CreditCardDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

  @Autowired
  private CreditCardStore creditCardStore;

  @Override
  public Mono<CreditCard> save(CreditCard creditCard) {

    return creditCardStore.save(
        CreditCardDocument.builder()
            .id(creditCard.getId())
            .description(creditCard.getDescription())
            .number(creditCard.getNumber())
            .limitCredit(creditCard.getLimitCredit())
            .customerId(creditCard.getCustomerId())
            .build())
        .map(creditCardDocument -> CreditCard.builder()
            .id(creditCardDocument.getId())
            .description(creditCardDocument.getDescription())
            .number(creditCardDocument.getNumber())
            .limitCredit(creditCardDocument.getLimitCredit())
            .customerId(creditCardDocument.getCustomerId()).build());
  }

  @Override
  public Mono<CreditCard> findById(String id) {

    return creditCardStore.findById(id).map(creditCardDocument -> CreditCard.builder()
        .id(creditCardDocument.getId())
        .description(creditCardDocument.getDescription())
        .number(creditCardDocument.getNumber())
        .limitCredit(creditCardDocument.getLimitCredit())
        .customerId(creditCardDocument.getCustomerId()).build());

  }

  @Override
  public Mono<CreditCard> findByNumber(String number) {

    return creditCardStore.findByNumber(number).map(creditCardDocument -> CreditCard.builder()
        .id(creditCardDocument.getId())
        .description(creditCardDocument.getDescription())
        .number(creditCardDocument.getNumber())
        .limitCredit(creditCardDocument.getLimitCredit())
        .customerId(creditCardDocument.getCustomerId()).build());

  }

  @Override
  public Flux<CreditCard> findAll() {

    return creditCardStore.findAll().map(creditCardDocument -> CreditCard.builder()
        .id(creditCardDocument.getId())
        .description(creditCardDocument.getDescription())
        .number(creditCardDocument.getNumber())
        .limitCredit(creditCardDocument.getLimitCredit())
        .customerId(creditCardDocument.getCustomerId()).build());
  }

}
