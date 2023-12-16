package com.nhom7.entity.employee.title;

public class HRManager extends Title{
    static {
        TitleFactory.instance().registerTitle(new HRManager());
    }
    public HRManager() {
        this.titleName = "HR Manager";
    }
}
