package com.example.employee.data.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Список работников
 */
public class EmployeeList {

    @SerializedName("response")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> list) {
        this.employees = list;
    }
}
