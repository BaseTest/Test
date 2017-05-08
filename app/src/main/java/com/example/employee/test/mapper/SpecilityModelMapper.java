package com.example.employee.test.mapper;

import com.example.employee.data.utils.MathUtils;
import com.example.employee.domain.mapper.BaseModelMapper;
import com.example.employee.test.model.Specialty;
import com.example.employee.test.model.SpecialtyEntity;
import java.util.List;

/**
 * Маппинг для должностей
 */
public class SpecilityModelMapper implements BaseModelMapper<com.example.employee.data.entity.Specialty, List<Specialty>> {

    @Override
    public void mapTo(List<com.example.employee.data.entity.Specialty> sourse, List<Specialty> dest) {
        sourse.forEach(employee -> assign(employee, dest));
    }

    @Override
    public void assign(com.example.employee.data.entity.Specialty sourse, List<Specialty> dest) {
        SpecialtyEntity specialty = new SpecialtyEntity();
        specialty.setId(MathUtils.toInt(sourse.getSpecialty_id()));
        specialty.setName(sourse.getName());
        dest.add(specialty);
    }
}
