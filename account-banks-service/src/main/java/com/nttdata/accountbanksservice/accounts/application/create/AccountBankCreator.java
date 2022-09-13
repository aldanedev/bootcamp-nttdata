package com.nttdata.accountbanksservice.accounts.application.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.accountbanksservice.accounts.application.AccountBankResponse;
import com.nttdata.accountbanksservice.accounts.domain.AccountBank;
import com.nttdata.accountbanksservice.accounts.domain.AccountBankRepository;
import com.nttdata.accountbanksservice.accounts.infrastructure.producer.TopicProducer;

import reactor.core.publisher.Mono;

@Service
public class AccountBankCreator {

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Autowired
  private TopicProducer topicProducer;

  public Mono<AccountBankResponse> create(Double maintenanceCommission, Integer monthlyMovements,
      String accountBankType, String customerId) {

    Mono<AccountBankResponse> response = accountBankRepository
        .save(AccountBank.create(maintenanceCommission, monthlyMovements,
            accountBankType, customerId))
        .map(AccountBankResponse::fromAccountBank);

    response.subscribe(a -> topicProducer.send(a.getId()));

    return response;
  }
}
