package com.sirma.employees.service;

import com.sirma.employees.model.Data;
import com.sirma.employees.model.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This class will be for service.
 *
 * @author khaled
 */
public class Service {

    /**
     * This Method for read file.
     *
     * @return List of data object.
     */
    public static List<Data> readFile() {
        List<Data> dataList = new ArrayList<>();
        try {
            File file = new File(
                    "resources" + File.separator + "employees.txt");
            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {

                // Ignore firstLine
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                Data data = prepareData(line);
                if (data != null)
                    dataList.add(data);
            }
        } catch (IOException e) {
            System.out.println("Exception occurred while reading the file: " + e);
        }

        return dataList;
    }

    /**
     * This method for prepare the data while reading the file.
     *
     * @param line string
     * @return Object of data.
     */
    private static Data prepareData(String line) {
        if (line != null && !line.isEmpty()) {
            String[] currentLine = line.split(",");
            if (currentLine.length == 4) {
                Data data = new Data();
                data.setEmpId(Integer.parseInt(currentLine[0].trim()));
                data.setProjectId(Integer.parseInt(currentLine[1].trim()));
                data.setDateFrom(convertToDate(currentLine[2].trim()));
                data.setDateTo(convertToDate(currentLine[3].trim()));
                return data;
            }
        }
        return null;
    }

    /**
     * This method to convert the date String to Date.
     *
     * @param dateString string
     * @return Object of data.
     */
    private static LocalDate convertToDate(String dateString) {
        if (dateString == null || dateString.isEmpty() || dateString.equalsIgnoreCase("null"))
            return LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * This method for returning Pair of employees that have worked as a team for the longest time.
     *
     * @param dataList List
     * @return Result object
     */
    public static Result returnEmployees(List<Data> dataList) {
        HashSet<Data> dataHashSet = new HashSet<>();
        Map<String, Result> resultMap = new HashMap<>();
        dataList.sort(Comparator.comparingInt(Data::getEmpId));

        for (Data data : dataList) {
            dataHashSet.add(data);
            for (Data data1 : dataList) {
                if (!dataHashSet.contains(data1)) {
                    if (!data.getDateFrom().isAfter(data1.getDateTo()) && !data.getDateTo().isBefore(data1.getDateFrom()) && data.getProjectId() == data1.getProjectId()) {
                        String key = data.getEmpId() + "," + data1.getEmpId();
                        LocalDate dateFrom;
                        LocalDate dateTo;
                        if (data.getDateFrom().isAfter(data1.getDateFrom())) dateFrom = data.getDateFrom();
                        else dateFrom = data1.getDateFrom();

                        if (data.getDateTo().isBefore(data1.getDateTo())) dateTo = data.getDateTo();
                        else dateTo = data1.getDateTo();

                        long days = ChronoUnit.DAYS.between(dateFrom, dateTo) + 1;
                        if (resultMap.containsKey(key)) {
                            resultMap.get(key).setWorkedDays(resultMap.get(key).getWorkedDays() + days);
                        } else {
                            resultMap.put(key, new Result(data.getEmpId(), data1.getEmpId(), days));
                        }
                    }
                }
            }
        }

        if (!resultMap.values().isEmpty()) {
            return resultMap.values().stream().max(Comparator.comparing(Result::getWorkedDays)).get();
        }

        return null;
    }
}
