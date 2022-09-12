package com.nttdata.creditsservice.credits.application.create;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.nttdata.creditsservice.credits.application.CreditCardMovementResponse;
import com.nttdata.creditsservice.credits.domain.CreditCard;
import com.nttdata.creditsservice.credits.domain.CreditCardMovement;
import com.nttdata.creditsservice.credits.domain.CreditCardMovementRepository;
import com.nttdata.creditsservice.credits.domain.CreditCardRepository;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditCardNotFoundException;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditLimitExceededException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardMovementCreator {
  private final CreditCardMovementRepository creditCardMovementRepository;
  private final CreditCardRepository creditCardRepository;

  public Mono<CreditCardMovementResponse> create(
      String description,
      Double amount,
      String creditCardId,
      String type) {

    Function<CreditCard, Mono<CreditCardMovementResponse>> validate = creditCard -> {
      return creditCardMovementRepository.findCreditCardIdBy(creditCard.getId())
          .reduce(creditCard.getLimitCredit() + amount, CreditCardMovement::sumMovements)
          .flatMap(amountTotal -> {
            if (amountTotal < 0) {
              return Mono.error(new CreditLimitExceededException("Credit limit exceeded"));
            }
            return creditCardMovementRepository.save(
                CreditCardMovement.create(description, amount, creditCardId, type))
                .map(creditCardMovement -> new CreditCardMovementResponse(
                    creditCardMovement.getId(),
                    creditCardMovement.getDescription(),
                    creditCardMovement.getAmount(),
                    creditCardMovement.getCreditCardId(),
                    creditCardMovement.getType().toString()));
          });
    };

    return creditCardRepository.findById(creditCardId)
        .flatMap(validate)
        .switchIfEmpty(Mono.error(new CreditCardNotFoundException("The credit card does not exist")));
  }
}
