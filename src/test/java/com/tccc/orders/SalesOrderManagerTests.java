// SalesOrderManagerTests.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.orders;

import com.tccc.models.SalesOrder;
import com.tccc.models.SalesOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesOrderManagerTests {

    SalesOrder salesOrder;
    SalesOrderManager salesOrderManager;

    @BeforeEach
    public void setup() {

        salesOrder = new SalesOrder();
        salesOrder.addItem(new SalesOrderItem() {{ setQuantity(1); setName("Green Widget"); setPrice(BigDecimal.valueOf(5.75)); }});
        salesOrder.addItem(new SalesOrderItem() {{ setQuantity(2); setName("Blue Widget"); setPrice(BigDecimal.valueOf(11.00)); }});

        salesOrderManager = new SalesOrderManager();
    }

    @Test
    public void orderIsCompleted() {

        String cardNumber = "378282246310005";

        assertTrue(salesOrderManager.completeOrder(salesOrder, cardNumber));
    }

    @Test
    public void zeroTotalIsRejected() {

        String cardNumber = "378282246310005";

        salesOrder = new SalesOrder();
        assertFalse(salesOrderManager.completeOrder(salesOrder, cardNumber));
    }
}
