package com.example.employee.test.di.component;

import com.example.employee.test.di.modules.AppModule;
import com.example.employee.test.di.modules.MappingModule;
import com.example.employee.test.di.modules.RestModule;
import com.example.employee.test.request.LoadRequest;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, RestModule.class, MappingModule.class})
public interface AppComponent {
    void inject(LoadRequest request);
}
