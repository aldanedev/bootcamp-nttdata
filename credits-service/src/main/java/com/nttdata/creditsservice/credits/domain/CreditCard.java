package com.nttdata.creditsservice.credits.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCard {
    private String id;
    private String description;
    private String number;
    private Double limitCredit;
    private String customerId;

    public CreditCard(String description, String number, Double limitCredit, String customerId) {
        this.description = description;
        this.number = number;
        this.limitCredit = limitCredit;
        this.customerId = customerId;
    }

    public static CreditCard create(String description, String number, Double limitCredit, String customerId) {
        return new CreditCard(
                description,
                number,
                limitCredit,
                customerId);

    }

    public static CreditCard update(String id, String description, String number, Double limitCredit,
            String customerId) {
        CreditCard creditCard = new CreditCard(
                description,
                number,
                limitCredit,
                customerId);
        creditCard.setId(id);
        return creditCard;
    }

}
