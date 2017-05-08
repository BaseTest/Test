package com.example.employee.data.cache;

import com.example.employee.data.entity.Employee;
import com.example.employee.data.exception.ExceptionHandler;
import com.example.employee.data.net.IRestResponse;
import com.example.employee.data.repository.RestManager;
import com.example.employee.domain.repository.BaseCache;
import java.util.ArrayList;
import java.util.List;

/**
 * Кеш работников
 */
// TODO не используется
public class EmployeeCache extends BaseListCache<Employee> implements BaseCache {

    @Override
    protected void getFromRest() {
        RestManager.getInstance().getEntitiesFromRest(response);
    }

    @Override
    protected List<Employee> getFromDB() {
        // TODO реализовать получение данных из бд
        return new ArrayList<>();
    }

    IRestResponse response = new IRestResponse() {
        @Override
        public void success(Object value) {
            if (value instanceof List)
                save((List<Employee>) value);
            // TODO else logging
        }

        @Override
        public void fail(Throwable e) {
            ExceptionHandler.handle(e, "Ошибка сервера");
        }
    };


    @Override
    public List<Employee> getValues() {
        return null;
    }

    @Override
    public Employee getValue() {
        return null;
    }
}
