// SalesOrder.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesOrder {

    private List<SalesOrderItem> items;
    private BigDecimal total;

    public SalesOrder() {

        items = new ArrayList<SalesOrderItem>();
        total = new BigDecimal(0);
    }

    public BigDecimal getTotal() {

        return total;
    }

    public List<SalesOrderItem> getItems() {

        return Collections.unmodifiableList(items);
    }

    public void addItem(SalesOrderItem item) {

        items.add(item);
        total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }

    public void removeItem(SalesOrderItem item) {

        items.remove(item);
        total = total.subtract(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }
}
