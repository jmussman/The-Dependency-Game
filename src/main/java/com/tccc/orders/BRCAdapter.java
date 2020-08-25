package com.tccc.orders;

import com.thebankofrandomcredit.cardservices.BankOfRandomCreditAuthorizer;

import java.math.BigDecimal;

public class BRCAdapter implements ICreditCardAuthorizer {

    @Override
    public String authorize(BigDecimal amt, String number) {

        BankOfRandomCreditAuthorizer authorizer = new BankOfRandomCreditAuthorizer();

        return authorizer.purchase(number, amt);
    }
}
