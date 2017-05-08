package com.example.employee.domain.repository;

import java.util.List;


public interface EmployeeRepository<T> {

    // void getEntitiesFromRest(IRestResponse response)

    List<T> getEntitiesFromRest();

}
