package com.example.employee.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.employee.data.utils.StringUtils;
import com.example.employee.test.BaseActivity;
import com.example.employee.test.R;
import com.example.employee.test.databinding.EmployeeItemBinding;
import com.example.employee.test.model.EmployeeEntity;
import com.example.employee.test.model.Specialty;
import com.example.employee.test.model.SpecialtyEntity;
import com.example.employee.test.model.SpecialtyItemEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.requery.android.QueryRecyclerAdapter;
import io.requery.query.Result;
import java.util.Random;

public class EmployeeActivity extends BaseActivity {

    @Override
    protected QueryRecyclerAdapter createAdapter() {
        return new EmployeeListAdapter();
    }

    @Override
    protected void doAfter(Bundle savedInstanceState) {
        if (filteredId == -1) {
            data.count(Specialty.class).get().single()
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) {
                            if (integer != 0)
                                processAdapter();
                            // TODO exception or creqte
                        }
                    });
        } else {
            data.findByKey(SpecialtyEntity.class, filteredId)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<SpecialtyEntity>() {
                        @Override
                        public void accept(SpecialtyEntity employee) {
                            processAdapter();
                        }
                    });
        }
    }

    @Override
    protected void doBefore(Bundle savedInstanceState) {
        setContentView(R.layout.activity_employee);
    }

    @Override
    protected String getActionBarTitle() {
        return "Список работников";
    }

    @Override
    protected String getFilteredKey() {
        return EXTRA_EMPLOYEE_ID;
    }

    @Override
    protected String getCurrentKey() {
        return EXTRA_SPECIALITY_ID;
    }

    // TODO вынести в базовый класс
    private class EmployeeListAdapter extends QueryRecyclerAdapter<EmployeeEntity,
            BindingHolder<EmployeeItemBinding>> implements View.OnClickListener {

        private final Random random = new Random();
        private final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};

        EmployeeListAdapter() {
            super(EmployeeEntity.$TYPE);
        }

        @Override
        public Result<EmployeeEntity> performQuery() {
            if (filteredId == -1)
                return data.select(EmployeeEntity.class).orderBy(EmployeeEntity.FIRST.lower()).get();
            else
                return data.select(EmployeeEntity.class)
                        .join(SpecialtyItemEntity.class).on(EmployeeEntity.ID.equal(SpecialtyItemEntity.EMPLOYEE_ID))
                        .join(SpecialtyEntity.class).on(SpecialtyItemEntity.SPECIALTY_ID.equal(SpecialtyEntity.ID))
                        .where(SpecialtyEntity.ID.equal(filteredId)).get();
        }

        @Override
        public void onBindViewHolder(EmployeeEntity item, BindingHolder<EmployeeItemBinding> holder,
                                     int position) {
            holder.binding.setEmployee(item);
            holder.binding.age.setText(StringUtils.getMenusIfNull(item.getAge()));
            holder.binding.picture.setBackgroundColor(colors[random.nextInt(colors.length)]);
        }

        @Override
        public BindingHolder<EmployeeItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            EmployeeItemBinding binding = EmployeeItemBinding.inflate(inflater);
            binding.getRoot().setTag(binding);
            binding.getRoot().setOnClickListener(this);
            return new BindingHolder<>(binding);
        }

        @Override
        public void onClick(View v) {
            EmployeeItemBinding binding = (EmployeeItemBinding) v.getTag();
            if (binding != null)
                startActivity(EmployeeOpenActivity.class, binding.getEmployee().getId());
        }
    }
}
