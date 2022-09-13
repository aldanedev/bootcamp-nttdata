package com.nttdata.accountbanksservice.accounts.infrastructure.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.accountbanksservice.accounts.infrastructure.mapping.AccountBankDocument;

@Repository
public interface AccountBankStore extends ReactiveMongoRepository<AccountBankDocument, String> {

}
