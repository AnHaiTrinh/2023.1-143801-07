package com.nhom7.entity.employee.title;

public class HeadOfDeparmentTitle extends Title{
    static {
        TitleFactory.instance().registerTitle(new HeadOfDeparmentTitle());
    }
    public HeadOfDeparmentTitle() {
        this.titleName = "Head of Department";
    }
}
