package com.tccc.orders;

import com.everyoneisauthorized.client.AlwaysAuthorize;

import java.math.BigDecimal;

public class EAAdapter implements ICreditCardAuthorizer {

    public String authorize(BigDecimal amt, String number) {

        AlwaysAuthorize authorizer = new AlwaysAuthorize();

        return authorizer.authorize(amt.doubleValue(), number);
    }
}
