// SalesOrderManager.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.orders;

import com.everyoneisauthorized.client.AlwaysAuthorize;
import com.tccc.models.SalesOrder;
import com.thebankofrandomcredit.cardservices.BankOfRandomCreditAuthorizer;

import java.math.BigDecimal;

public class SalesOrderManager {

    private AlwaysAuthorize authorizer;

    public SalesOrderManager() {

        authorizer = new AlwaysAuthorize();
    }

    public boolean completeOrder(SalesOrder salesOrder, String card) {

        return salesOrder.getTotal().equals(BigDecimal.ZERO) != true &&
                authorizer.authorize(salesOrder.getTotal().doubleValue(), card) != null;
    }
}
