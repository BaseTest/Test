package com.example.employee.test.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.employee.domain.mapper.BaseModelMapper;
import com.example.employee.test.di.CustomScope;
import com.example.employee.test.mapper.EmployeeModelMapper;
import com.example.employee.test.mapper.SpecilityModelMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class MappingModule {

    private BaseModelMapper mapper;

    public MappingModule(@NonNull BaseModelMapper mapper) {
        this.mapper = mapper;
    }

    @Provides
    @CustomScope
    @NonNull
    public SpecilityModelMapper provideBaseModelMapper() {
        return (SpecilityModelMapper) mapper;
    }

    @Provides
    @Singleton
    @NonNull
    public EmployeeModelMapper provideBaseModelMapper2() {
        return (EmployeeModelMapper) mapper;
    }
}