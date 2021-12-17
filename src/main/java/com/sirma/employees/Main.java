package com.sirma.employees;

import com.sirma.employees.model.Data;
import com.sirma.employees.model.Result;
import com.sirma.employees.service.Service;

import java.util.List;

/**
 * This class will be the Main class.
 *
 * @author khaled
 */
public class Main {

    public static void main(String[] args) {
        List<Data> dataList = Service.readFile();
        if (!dataList.isEmpty()) {
            Result result = Service.returnEmployees(dataList);
            if (result == null)
                System.out.println("No result found.");
            else
                System.out.println("Pair of employees that have worked as a team for the longest time are: <<("
                        + result.getEmpId1() + ", " + result.getEmpId2() + ")>> worked together in <<" + result.getWorkedDays() + ">> days.");
        }
    }

}
