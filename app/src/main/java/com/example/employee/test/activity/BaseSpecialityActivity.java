package com.example.employee.test.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.employee.test.BaseActivity;
import com.example.employee.test.databinding.SpecialityItemBinding;
import com.example.employee.test.model.EmployeeEntity;
import com.example.employee.test.model.SpecialtyEntity;
import com.example.employee.test.model.SpecialtyItemEntity;
import io.requery.android.QueryRecyclerAdapter;
import io.requery.query.Result;
import java.util.Random;

/**
 * Базовая активити содержащая список должностей
 */
public abstract class BaseSpecialityActivity extends BaseActivity {

    @Override
    protected QueryRecyclerAdapter createAdapter() {
        return new SpecialtyAdapter();
    }

    @Override
    protected String getFilteredKey() {
        return EXTRA_SPECIALITY_ID;
    }

    @Override
    protected String getCurrentKey() {
        return EXTRA_EMPLOYEE_ID;
    }

    private class SpecialtyAdapter extends QueryRecyclerAdapter<SpecialtyEntity,
            BindingHolder<SpecialityItemBinding>> implements View.OnClickListener {

        private final Random random = new Random();
        private final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};

        SpecialtyAdapter() {
            super(SpecialtyEntity.$TYPE);
        }

        @Override
        public Result<SpecialtyEntity> performQuery() {
            if (filteredId == -1)
                // TODO нажата кнопка Home показываем всех кто есть
                return data.select(SpecialtyEntity.class).orderBy(SpecialtyEntity.NAME.lower()).get();
            else
                return data.select(SpecialtyEntity.class)
                        .join(SpecialtyItemEntity.class).on(SpecialtyEntity.ID.equal(SpecialtyItemEntity.SPECIALTY_ID))
                        .join(EmployeeEntity.class).on(SpecialtyItemEntity.EMPLOYEE_ID.equal(EmployeeEntity.ID))
                        .where(EmployeeEntity.ID.equal(filteredId)).get();
        }

        @Override
        public void onBindViewHolder(SpecialtyEntity item, BindingHolder<SpecialityItemBinding> holder,
                                     int position) {
            holder.binding.setSpeciality(item);
            holder.binding.picture.setBackgroundColor(colors[random.nextInt(colors.length)]);
        }

        @Override
        public BindingHolder<SpecialityItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            SpecialityItemBinding binding = SpecialityItemBinding.inflate(inflater);
            binding.getRoot().setTag(binding);
            binding.getRoot().setOnClickListener(this);
            return new BindingHolder<>(binding);
        }

        @Override
        public void onClick(View v) {
            // Возврат к списку работников для выбранной должности
            SpecialityItemBinding binding = (SpecialityItemBinding) v.getTag();
            if (binding != null)
                startActivity(EmployeeActivity.class, binding.getSpeciality().getId());
        }
    }
}
