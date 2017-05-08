package com.example.employee.test.di.modules;

import android.support.annotation.NonNull;
import com.example.employee.data.repository.RestManager;
import com.example.employee.domain.repository.EmployeeRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RestModule {

    @Provides
    @Singleton
    @NonNull
    public EmployeeRepository provideRestManager() {
        return new RestManager();
    }
}
