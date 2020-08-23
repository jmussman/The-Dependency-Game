// SalesOrderItem.java
// Copyright © 2020 Joel Mussman. All rights reserved.
//

package com.tccc.models;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesOrderItem {

    private int quantity;
    private String name;
    private BigDecimal price;

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrderItem that = (SalesOrderItem) o;
        return quantity == that.quantity &&
                name.equals(that.name) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(quantity, name, price);
    }
}
