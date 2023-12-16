package com.nhom7.entity.employee.title;

public class WorkerTitle extends Title {
    static {
        TitleFactory.instance().registerTitle(new WorkerTitle());
    }
    public WorkerTitle() {
        this.titleName = "Worker";
    }
}
