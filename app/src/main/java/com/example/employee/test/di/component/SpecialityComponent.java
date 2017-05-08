package com.example.employee.test.di.component;

import com.example.employee.test.di.CustomScope;
import com.example.employee.test.di.modules.MappingModule;
import com.example.employee.test.mapper.EmployeeModelMapper;
import dagger.Component;

@CustomScope
@Component(dependencies = AppComponent.class, modules = {MappingModule.class})
public interface SpecialityComponent {
    void inject(EmployeeModelMapper mapper);
}
