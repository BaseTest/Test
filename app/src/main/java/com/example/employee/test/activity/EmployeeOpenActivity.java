package com.example.employee.test.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.employee.data.utils.DateUtils;
import com.example.employee.data.utils.StringUtils;
import com.example.employee.test.R;
import com.example.employee.test.databinding.ActivityOpenEmployeeBinding;
import com.example.employee.test.model.EmployeeEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class EmployeeOpenActivity extends BaseSpecialityActivity {

    private EmployeeEntity employee;
    private ActivityOpenEmployeeBinding binding;

    @Override
    protected String getActionBarTitle() {
        return "Сотрудник";
    }

    @Override
    protected void doAfter(Bundle savedInstanceState) {
        if (filteredId == -1) {
            employee = new EmployeeEntity();
            binding.setEmployee(employee);
        } else {
            data.findByKey(EmployeeEntity.class, filteredId)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<EmployeeEntity>() {
                        @Override
                        public void accept(EmployeeEntity employee) {
                            initEnity(employee);
                            processAdapter();
                        }
                    });
        }
    }

    @Override
    protected void doBefore(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_open_employee);
    }

    private void initEnity(EmployeeEntity entity) {
        employee = entity;
        binding.setEmployee(employee);
        // TODO Glide вынести в утилитный класс
        Glide.with(EmployeeOpenActivity.this).load(employee.getImage()).placeholder(R.drawable.smile).into(binding.picture);
        binding.age.setText(StringUtils.getMenusIfNull(EmployeeOpenActivity.this.employee.getAge()));
        binding.birthday.setText(StringUtils.getMenusIfNull(DateUtils.toString(EmployeeOpenActivity.this.employee.getBirthday())));
    }
}
