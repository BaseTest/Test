package com.example.employee.domain.mapper;

import java.util.List;

public interface BaseModelMapper<T, E extends List> {
    void mapTo(List<T> sourse, E dest);

    void assign(T sourse, E dest);
}
