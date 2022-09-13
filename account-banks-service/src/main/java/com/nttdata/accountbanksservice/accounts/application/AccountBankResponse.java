package com.nttdata.accountbanksservice.accounts.application;

import com.nttdata.accountbanksservice.accounts.domain.AccountBank;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountBankResponse {
  private String id;
  private String customerId;
  private Double maintenanceCommission;
  private int monthlyMovements;
  private String accountBankType;

  public static AccountBankResponse fromAccountBank(AccountBank accountBank) {
    return AccountBankResponse.builder()
        .id(accountBank.getId())
        .customerId(accountBank.getCustomerId())
        .maintenanceCommission(accountBank.getMaintenanceCommission())
        .monthlyMovements(accountBank.getMonthlyMovements())
        .accountBankType(accountBank.getAccountBankType().name())
        .build();
  }
}