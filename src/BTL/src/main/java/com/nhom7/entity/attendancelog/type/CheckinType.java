package com.nhom7.entity.attendancelog.type;

public class CheckinType extends Type{
    static {
        TypeFactory.instance().registerType(new CheckinType());
    }
    public CheckinType() {
        this.typeName = "CHECKIN";
    }
}
