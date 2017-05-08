package com.example.employee.data.cache;

import com.example.employee.data.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Базовый кеш для списка
 */
abstract class BaseListCache<T> {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    List<T> values = new ArrayList<T>();

    /**
     * Получение данных с сервера
     */
    protected abstract void getFromRest();

    /**
     * Плдучение данных из кеша
     */
    public List<T> getItems() {
        List<T> restlt = getFromDB();
        if (restlt.size() == 0)
            getFromRest();
        return restlt;
    }

    protected abstract List<T> getFromDB();

    protected void save(List<Employee> value) {
       // TODO реализовать сохранение
    }

}
