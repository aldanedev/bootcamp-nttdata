package com.nttdata.creditsservice.credits.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreditCardMovement {
    private String id;
    private String description;
    private Double amount;
    private String creditCardId;
    private MovementType type;

    public static Double sumMovements(Double sum, CreditCardMovement movement) {

        if (movement.getType() == MovementType.CREDIT) {
            return sum - movement.getAmount();
        } else {
            return sum + movement.getAmount();
        }
    }

    public Double getAmountSigned() {
        return this.type == MovementType.DEBIT ? this.amount : -this.amount;
    }

    public CreditCardMovement(String description, Double amount, String creditCardId, String type) {
        this.description = description;
        this.amount = amount;
        this.creditCardId = creditCardId;
        this.type = MovementType.valueOf(type);
    }

    public static CreditCardMovement create(String description, Double amount, String creditCardId, String type) {
        return new CreditCardMovement(description, amount, creditCardId, type);
    }

}
