package com.nttdata.accountbanksservice.accounts.infrastructure.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "account_banks")
public class AccountBankDocument {
  @Id
  private String id;
  private String customerId;
  private Double maintenanceCommission;
  private int monthlyMovements;
  private String accountBankType;

}
