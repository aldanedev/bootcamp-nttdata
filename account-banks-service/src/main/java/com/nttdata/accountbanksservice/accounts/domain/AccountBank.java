package com.nttdata.accountbanksservice.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountBank {

  protected String id;
  private String customerId;
  private Double maintenanceCommission;
  private int monthlyMovements;
  private AccountBankType accountBankType;

  public AccountBank(Double maintenanceCommission, int monthlyMovements, String accountBankType, String customerId) {
    this.maintenanceCommission = maintenanceCommission;
    this.monthlyMovements = monthlyMovements;
    this.accountBankType = AccountBankType.valueOf(accountBankType);
    this.customerId = customerId;
  }

  public static AccountBank create(Double maintenanceCommission, int monthlyMovements,
      String accountBankType, String customerId) {
    return new AccountBank(maintenanceCommission, monthlyMovements, accountBankType, customerId);
  }

}
