package com.nttdata.creditsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nttdata.creditsservice.controllers.request.CreditCardRequest;
import com.nttdata.creditsservice.controllers.request.ErrorMessage;
import com.nttdata.creditsservice.credits.application.CreditCardResponse;
import com.nttdata.creditsservice.credits.application.create.CreditCardCreator;
import com.nttdata.creditsservice.credits.application.find.CreditCardFinder;
import com.nttdata.creditsservice.credits.application.search_all.AllCreditCardsSearcher;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditCardNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@RequestMapping("/api/v1/credits/creditCards")
class CreditCardController {

    @Autowired
    private CreditCardCreator creditCardCreator;

    @Autowired
    private AllCreditCardsSearcher allCreditCardsSearcher;

    @Autowired
    private CreditCardFinder creditCardFinder;

    @GetMapping
    public ResponseEntity<Flux<CreditCardResponse>> getCreditCards() {

        return ResponseEntity.ok(allCreditCardsSearcher.searchAll());
    }

    @PostMapping
    public Mono<ResponseEntity<CreditCardResponse>> addCredit(@RequestBody CreditCardRequest creditCardRequest) {

        return creditCardCreator.create(
                creditCardRequest.getDescription(),
                creditCardRequest.getNumber(),
                creditCardRequest.getLimitCredit(),
                creditCardRequest.getCustomerId()).map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreditCardResponse>> getCreditCard(@PathVariable String id) {
        return creditCardFinder.find(id).map(c -> ResponseEntity.ok(c));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreditCardResponse>> updateCreditCard(@PathVariable String id,
            @RequestBody CreditCardRequest creditCard) {
        return creditCardCreator.update(
                id,
                creditCard.getDescription(),
                creditCard.getNumber(),
                creditCard.getLimitCredit(),
                creditCard.getCustomerId()).map(c -> ResponseEntity.ok(c));

    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    Mono<ResponseEntity<ErrorMessage>> notFound(Exception e) {
        return Mono.just(ResponseEntity.notFound().build());
    }

}