// SalesOrderManager.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.orders;

import com.tccc.models.SalesOrder;
import com.thebankofrandomcredit.cardservices.BankOfRandomCreditAuthorizer;

import java.math.BigDecimal;

public class SalesOrderManager {

    private BankOfRandomCreditAuthorizer authorizer = new BankOfRandomCreditAuthorizer();

    public boolean completeOrder(SalesOrder salesOrder, String card) {

        if (salesOrder.getTotal().equals(BigDecimal.ZERO)) {

            return false;
        }

        if (authorizer.purchase(card, salesOrder.getTotal()) != null) {

            return true;

        } else {

            return false;
        }
    }
}
