package com.sirma.employees.model;

import java.io.Serializable;

public class Result implements Serializable {
    private int empId1;
    private int empId2;
    private long workedDays;

    public Result() {
    }

    public Result(int empId1, int empId2, long workedDays) {
        this.empId1 = empId1;
        this.empId2 = empId2;
        this.workedDays = workedDays;
    }

    public int getEmpId1() {
        return empId1;
    }

    public void setEmpId1(int empId1) {
        this.empId1 = empId1;
    }

    public int getEmpId2() {
        return empId2;
    }

    public void setEmpId2(int empId2) {
        this.empId2 = empId2;
    }

    public long getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(long workedDays) {
        this.workedDays = workedDays;
    }
}
