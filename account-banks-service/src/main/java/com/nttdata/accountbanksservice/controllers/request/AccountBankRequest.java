package com.nttdata.accountbanksservice.controllers.request;

import lombok.Data;

@Data
public class AccountBankRequest {
  private String customerId;
  private Double maintenanceCommission;
  private int monthlyMovements;
  private String accountBankType;
}
