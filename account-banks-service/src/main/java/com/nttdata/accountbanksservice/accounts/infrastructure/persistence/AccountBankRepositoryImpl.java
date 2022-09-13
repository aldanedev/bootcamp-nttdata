package com.nttdata.accountbanksservice.accounts.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.accountbanksservice.accounts.domain.AccountBank;
import com.nttdata.accountbanksservice.accounts.domain.AccountBankRepository;
import com.nttdata.accountbanksservice.accounts.domain.AccountBankType;
import com.nttdata.accountbanksservice.accounts.infrastructure.mapping.AccountBankDocument;

import reactor.core.publisher.Mono;

@Repository
public class AccountBankRepositoryImpl implements AccountBankRepository {

  @Autowired
  private AccountBankStore accountBankStore;

  @Override
  public Mono<AccountBank> save(AccountBank accountBank) {

    return accountBankStore.save(
        AccountBankDocument.builder()
            .customerId(accountBank.getCustomerId())
            .maintenanceCommission(accountBank.getMaintenanceCommission())
            .monthlyMovements(accountBank.getMonthlyMovements())
            .accountBankType(accountBank.getAccountBankType().name())
            .build())
        .map(accountBankDocument -> AccountBank.builder()
            .id(accountBankDocument.getId())
            .customerId(accountBankDocument.getCustomerId())
            .maintenanceCommission(accountBankDocument.getMaintenanceCommission())
            .monthlyMovements(accountBankDocument.getMonthlyMovements())
            .accountBankType(AccountBankType.valueOf(accountBankDocument.getAccountBankType()))
            .build());

  }

}
