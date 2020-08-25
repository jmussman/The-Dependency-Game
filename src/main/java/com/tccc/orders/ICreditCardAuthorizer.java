package com.tccc.orders;

import java.math.BigDecimal;

public interface ICreditCardAuthorizer {

    String authorize(BigDecimal amt, String number);
}
