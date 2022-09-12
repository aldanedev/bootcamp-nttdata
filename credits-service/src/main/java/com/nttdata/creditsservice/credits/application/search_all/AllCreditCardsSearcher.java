package com.nttdata.creditsservice.credits.application.search_all;

import org.springframework.stereotype.Service;

import com.nttdata.creditsservice.credits.application.CreditCardResponse;
import com.nttdata.creditsservice.credits.domain.CreditCardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AllCreditCardsSearcher {
  public final CreditCardRepository creditCardRepository;

  public Flux<CreditCardResponse> searchAll() {
    return creditCardRepository.findAll().map(CreditCardResponse::fromCreditCard);
  }
}
