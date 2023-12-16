package com.nhom7.entity.attendancelog.type;

public class CheckoutType extends Type {
    static {
        TypeFactory.instance().registerType(new CheckoutType());
    }
    public CheckoutType() {
        this.typeName = "CHECKOUT";
    }
}
