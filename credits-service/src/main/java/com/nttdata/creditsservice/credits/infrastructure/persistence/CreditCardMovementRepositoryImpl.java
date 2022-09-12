package com.nttdata.creditsservice.credits.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.creditsservice.credits.domain.CreditCardMovement;
import com.nttdata.creditsservice.credits.domain.CreditCardMovementRepository;
import com.nttdata.creditsservice.credits.infrastructure.mapping.CreditCardMovementDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreditCardMovementRepositoryImpl implements CreditCardMovementRepository {

  @Autowired
  private CreditCardMovementStore creditCardMovementStore;

  @Override
  public Mono<CreditCardMovement> save(CreditCardMovement creditCardMovement) {

    return creditCardMovementStore.save(
        CreditCardMovementDocument.builder()
            .id(creditCardMovement.getId())
            .description(creditCardMovement.getDescription())
            .amount(creditCardMovement.getAmount())
            .creditCardId(creditCardMovement.getCreditCardId())
            .build())
        .map(creditCardMovementDocument -> CreditCardMovement.builder()
            .id(creditCardMovementDocument.getId())
            .description(creditCardMovementDocument.getDescription())
            .amount(creditCardMovementDocument.getAmount())
            .creditCardId(creditCardMovementDocument.getCreditCardId())
            .build());

  }

  @Override
  public Flux<CreditCardMovement> findCreditCardIdBy(String id) {

    return creditCardMovementStore.findByCreditCardId(id)
        .map(creditCardMovementDocument -> CreditCardMovement.builder()
            .id(creditCardMovementDocument.getId())
            .description(creditCardMovementDocument.getDescription())
            .amount(creditCardMovementDocument.getAmount())
            .creditCardId(creditCardMovementDocument.getCreditCardId())
            .build());
  }

}
