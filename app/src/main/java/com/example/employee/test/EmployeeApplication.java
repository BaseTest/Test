package com.example.employee.test;

import android.app.Application;
import android.os.StrictMode;
import com.example.employee.data.exception.ExceptionHandler;
import com.example.employee.test.di.component.AppComponent;
import com.example.employee.test.di.component.DaggerAppComponent;
import com.example.employee.test.di.component.DaggerSpecialityComponent;
import com.example.employee.test.di.component.SpecialityComponent;
import com.example.employee.test.di.modules.AppModule;
import com.example.employee.test.di.modules.MappingModule;
import com.example.employee.test.mapper.EmployeeModelMapper;
import com.example.employee.test.mapper.SpecilityModelMapper;
import com.example.employee.test.model.Models;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;


public class EmployeeApplication extends Application {

    private ReactiveEntityStore<Persistable> dataStore;

    private static AppComponent component;

    private static SpecialityComponent specialityComponent;

    public static AppComponent getAppComponent() {
        return component;
    }

    public static SpecialityComponent getSpecialityComponent() {
        return specialityComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        component = buildComponent();
        specialityComponent = buildSpecialityComponent();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                ExceptionHandler.handle(paramThrowable, "Ошибка в приложении");
            }
        });

    }

    private SpecialityComponent buildSpecialityComponent() {
        return DaggerSpecialityComponent.builder()
                .appComponent(component)
                .mappingModule(new MappingModule(new SpecilityModelMapper()))
                .build();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .mappingModule(new MappingModule(new EmployeeModelMapper()))
                .build();
    }

    private String DEFAULT_DB_NAME = "employee.db";

    // TODO отделить можель от биндинга и вынести в data
    public ReactiveEntityStore<Persistable> getData() {
        if (dataStore == null) {
            DatabaseSource source = new DatabaseSource(this, Models.DEFAULT, DEFAULT_DB_NAME, 1);
            if (io.requery.android.BuildConfig.DEBUG)
                source.setTableCreationMode(TableCreationMode.DROP_CREATE);
            Configuration configuration = source.getConfiguration();
            dataStore = ReactiveSupport.toReactiveStore(
                    new EntityDataStore<Persistable>(configuration));
        }
        return dataStore;
    }
}
