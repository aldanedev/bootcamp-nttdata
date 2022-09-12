package com.nttdata.creditsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nttdata.creditsservice.controllers.request.CreditCardMovementRequest;
import com.nttdata.creditsservice.controllers.request.ErrorMessage;
import com.nttdata.creditsservice.credits.application.CreditCardMovementResponse;
import com.nttdata.creditsservice.credits.application.create.CreditCardMovementCreator;
import com.nttdata.creditsservice.credits.domain.exceptions.CreditCardNotFoundException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
@RequestMapping("/api/v1/credits/creditCardMovements")

public class CreditCardMovementController {
    @Autowired
    private CreditCardMovementCreator creditCardMovementCreator;

    @PostMapping
    public Mono<ResponseEntity<CreditCardMovementResponse>> addMovement(
            @RequestBody CreditCardMovementRequest creditCardMovementRequest) {

        return creditCardMovementCreator.create(
                creditCardMovementRequest.getDescription(),
                creditCardMovementRequest.getAmount(),
                creditCardMovementRequest.getCreditCardId(),
                creditCardMovementRequest.getType()).map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));

    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    Mono<ResponseEntity<ErrorMessage>> notFound(Exception e) {
        return Mono.just(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    Mono<ResponseEntity<ErrorMessage>> badRequest(Exception e) {
        return Mono.just(ResponseEntity.badRequest().build());
    }

}
