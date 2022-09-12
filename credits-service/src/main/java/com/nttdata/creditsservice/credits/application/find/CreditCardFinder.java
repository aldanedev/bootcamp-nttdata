package com.nttdata.creditsservice.credits.application.find;

import org.springframework.stereotype.Service;

import com.nttdata.creditsservice.credits.application.CreditCardResponse;
import com.nttdata.creditsservice.credits.domain.CreditCardRepository;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditCardNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardFinder {
  private final CreditCardRepository creditCardRepository;

  public Mono<CreditCardResponse> find(String id) {
    return creditCardRepository.findById(id).map(CreditCardResponse::fromCreditCard)
        .switchIfEmpty(Mono.error(new CreditCardNotFoundException(id)));
  }
}
