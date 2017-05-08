package com.example.employee.test.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }

    // TOOD не используется
    @Singleton
    @NonNull
    @Provides
    Context provideContext() {
        return appContext;
    }
}
