package com.example.employee.data.repository;

import com.example.employee.data.entity.Employee;
import com.example.employee.data.exception.ExceptionHandler;
import com.example.employee.data.net.ConnectionApi;
import com.example.employee.data.net.IRestResponse;
import com.example.employee.domain.repository.EmployeeRepository;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class RestManager implements EmployeeRepository {

    private static final String BASE_URL = "http://178.62.196.215/images/testTask.json";

    private static volatile RestManager instance;

    public static RestManager getInstance() {
        RestManager localInstance = instance;
        if (localInstance == null) {
            synchronized (RestManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RestManager();
                }
            }
        }
        return localInstance;
    }

    private void getEntitiesFromApi(IRestResponse response) {
        try {
            ConnectionApi.createGET(BASE_URL).requestAsyncCall(response);
        } catch (MalformedURLException e) {
            ExceptionHandler.handle(e, "Ошибка создания поделючения");
        }
    }

    public void getEntitiesFromRest(IRestResponse response) {
        getEntitiesFromApi(response);
    }

    private List<Employee> getEntitiesFromApi() {
        try {
            return ConnectionApi.createGET(BASE_URL).requestSyncCall();
        } catch (Exception e) {
            ExceptionHandler.handle(e, "Ошибка получения данных");
        }
        return new ArrayList<>();
    }

    public List<Employee> getEntitiesFromRest() {
        return getEntitiesFromApi();
    }
}
