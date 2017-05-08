package com.example.employee.test.mapper;

import com.example.employee.data.utils.DateUtils;
import com.example.employee.data.utils.StringUtils;
import com.example.employee.domain.mapper.BaseModelMapper;
import com.example.employee.test.EmployeeApplication;
import com.example.employee.test.model.Employee;
import com.example.employee.test.model.EmployeeEntity;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;

/**
 * Маппинг для работнков
 */
public class EmployeeModelMapper implements BaseModelMapper<com.example.employee.data.entity.Employee, List<Employee>> {

    @Inject
    SpecilityModelMapper baseModelMapper;

    private SpecilityModelMapper getSpecilityModelMapper() {
        if (baseModelMapper == null)
            EmployeeApplication.getSpecialityComponent().inject(this);
        return baseModelMapper;
    }

    public EmployeeModelMapper() {
    }

    @Override
    public void mapTo(List<com.example.employee.data.entity.Employee> list, final List<Employee> result) {
        list.forEach(employee -> assign(employee, result));
    }

    @Override
    public void assign(com.example.employee.data.entity.Employee sourse, List<Employee> dest) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirst(StringUtils.toCamelCase(sourse.getFirstName()));
        employee.setLast(StringUtils.toCamelCase(sourse.getSecondName()));
        employee.setBirthday(sourse.getBirthday());
        employee.setAge(DateUtils.getAge(sourse.getBirthday()));
        employee.setImage(sourse.getUrl());
        getSpecilityModelMapper().mapTo(sourse.getSpecialtys(), employee.getSpecialtyList());
        dest.add(employee);
    }

}
