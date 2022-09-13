package com.nttdata.accountbanksservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.accountbanksservice.accounts.application.AccountBankResponse;
import com.nttdata.accountbanksservice.accounts.application.create.AccountBankCreator;
import com.nttdata.accountbanksservice.controllers.request.AccountBankRequest;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accountBanks")
public class AccountBankController {

  @Autowired
  private AccountBankCreator accountBankCreator;

  @PostMapping
  public Mono<ResponseEntity<AccountBankResponse>> register(@RequestBody AccountBankRequest accountBankRequest) {

    return accountBankCreator.create(
        accountBankRequest.getMaintenanceCommission(),
        accountBankRequest.getMonthlyMovements(),
        accountBankRequest.getAccountBankType(),
        accountBankRequest.getCustomerId())
        .flatMap(accountBank -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(accountBank)));

  }
}
