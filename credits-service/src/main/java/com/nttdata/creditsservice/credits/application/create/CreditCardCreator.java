package com.nttdata.creditsservice.credits.application.create;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.nttdata.creditsservice.credits.application.CreditCardResponse;
import com.nttdata.creditsservice.credits.domain.CreditCard;
import com.nttdata.creditsservice.credits.domain.CreditCardRepository;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditCardAlreadyExists;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardCreator {
  private final CreditCardRepository creditCardRepository;

  public Mono<CreditCardResponse> create(String description, String number, Double limitCredit, String customerId) {
    Function<Boolean, Mono<CreditCardResponse>> exists = value -> {
      if (value) {
        return Mono.error(new CreditCardAlreadyExists("Credit card already exists"));
      }
      return creditCardRepository.save(CreditCard.create(description, number, limitCredit, customerId))
          .map(CreditCardResponse::fromCreditCard);

    };

    return creditCardRepository.findByNumber(number).hasElement().flatMap(exists);
  }

  public Mono<CreditCardResponse> update(String id, String description, String number, Double limitCredit,
      String customerId) {
    return creditCardRepository.findById(id)
        .map(creditCard -> CreditCard.update(id, description, number, limitCredit, customerId))
        .flatMap(creditCardRepository::save).map(CreditCardResponse::fromCreditCard);
  }
}
