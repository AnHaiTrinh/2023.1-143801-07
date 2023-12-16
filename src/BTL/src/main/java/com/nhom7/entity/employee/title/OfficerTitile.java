package com.nhom7.entity.employee.title;

public class OfficerTitile extends Title{
    static {
        TitleFactory.instance().registerTitle(new OfficerTitile());
    }
    public OfficerTitile() {
        this.titleName = "Officer";
    }
}
