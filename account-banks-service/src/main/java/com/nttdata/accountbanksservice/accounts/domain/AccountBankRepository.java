package com.nttdata.accountbanksservice.accounts.domain;

import reactor.core.publisher.Mono;

public interface AccountBankRepository {
  public Mono<AccountBank> save(AccountBank accountBank);
}
