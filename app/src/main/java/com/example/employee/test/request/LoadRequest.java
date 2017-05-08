package com.example.employee.test.request;

import com.example.employee.domain.repository.EmployeeRepository;
import com.example.employee.test.EmployeeApplication;
import com.example.employee.test.mapper.EmployeeModelMapper;
import com.example.employee.test.model.Employee;
import io.reactivex.Observable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class LoadRequest implements Callable<Observable<Iterable<Employee>>> {

    @Inject
    EmployeeModelMapper baseModelMapper;

    @Inject
    EmployeeRepository restManager;

    private final ReactiveEntityStore<Persistable> data;

    public LoadRequest(ReactiveEntityStore<Persistable> data) {
        EmployeeApplication.getAppComponent().inject(this);
        this.data = data;
    }

    @Override
    public Observable<Iterable<Employee>> call() {
        List<com.example.employee.data.entity.Employee> employeeList = restManager.getEntitiesFromRest();
        if (employeeList == null)
            return null;
        List<Employee> result = new ArrayList<>();
        baseModelMapper.mapTo(employeeList, result);
        Observable<Iterable<Employee>> call = data.insert(result).toObservable();
        return call;
    }
}
