// SalesOrderTests.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesOrderTests {


    SalesOrder salesOrder;

    @BeforeEach
    public void setup() {

        salesOrder = new SalesOrder();
    }

    @Test
    public void addSingleItemIsInList() {

        SalesOrderItem item = new SalesOrderItem() {{ setQuantity(1); setName("Green Widget"); setPrice(BigDecimal.valueOf(5.75)); }};
        salesOrder.addItem(item);

        assertTrue(salesOrder.getItems().contains(item));
    }

    @Test
    public void addTwoItemsAreInList() {

        SalesOrderItem item1 = new SalesOrderItem() {{ setQuantity(1); setName("Green Widget"); setPrice(BigDecimal.valueOf(5.75)); }};
        SalesOrderItem item2 = new SalesOrderItem() {{ setQuantity(2); setName("Blue Widget"); setPrice(BigDecimal.valueOf(11.00)); }};
        salesOrder.addItem(item1);
        salesOrder.addItem(item2);

        assertTrue(salesOrder.getItems().contains(item1) && salesOrder.getItems().contains(item2));
    }

    @Test
    public void addSingleItemSetsTotal() {

        SalesOrderItem item = new SalesOrderItem() {{ setQuantity(1); setName("Green Widget"); setPrice(BigDecimal.valueOf(5.75)); }};
        salesOrder.addItem(item);

        assertEquals(BigDecimal.valueOf(5.75), salesOrder.getTotal().stripTrailingZeros());
    }

    @Test
    public void addTwoItemsSetsTotal() {

        salesOrder.addItem(new SalesOrderItem() {{ setQuantity(2); setName("Green Widget"); setPrice(BigDecimal.valueOf(5.75)); }});

        assertEquals(BigDecimal.valueOf(11.5), salesOrder.getTotal().stripTrailingZeros());
    }
}
