package com.sirma.employees.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class will contain the data which loaded from the text file.
 *
 * @author khaled
 */
public class Data implements Serializable {

    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Data() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return empId == data.empId && projectId == data.projectId && Objects.equals(dateFrom, data.dateFrom) && Objects.equals(dateTo, data.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, projectId, dateFrom, dateTo);
    }
}
