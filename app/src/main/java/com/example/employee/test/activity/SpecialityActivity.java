package com.example.employee.test.activity;

import android.os.Bundle;
import com.example.employee.test.R;
import com.example.employee.test.model.Employee;
import com.example.employee.test.model.Specialty;
import com.example.employee.test.request.LoadRequest;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SpecialityActivity extends BaseSpecialityActivity {

    @Override
    protected String getActionBarTitle() {
        return "Список должностей";
    }

    @Override
    protected void doAfter(Bundle savedInstanceState) {
        data.count(Specialty.class).get().single()
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (integer == 0) {
                            Observable.fromCallable(new LoadRequest(data))
                                    .flatMap(new Function<Observable<Iterable<Employee>>, Observable<?>>() {
                                        @Override
                                        public Observable<?> apply(Observable<Iterable<Employee>> o) {
                                            return o;
                                        }
                                    })
                                    .observeOn(Schedulers.computation())
                                    .subscribe(new Consumer<Object>() {
                                        @Override
                                        public void accept(Object o) {
                                            processAdapter();
                                        }
                                    });
                        }
                    }
                });
    }

    @Override
    protected void doBefore(Bundle savedInstanceState) {
        setContentView(R.layout.activity_speciality);
    }
}