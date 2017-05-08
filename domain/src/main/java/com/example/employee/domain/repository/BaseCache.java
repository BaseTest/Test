package com.example.employee.domain.repository;

import java.util.List;

public interface BaseCache<T> {
    List<T> getValues();

    T getValue();
}
