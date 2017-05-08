package com.example.employee.test.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Generated;
import io.requery.JunctionTable;
import io.requery.Key;
import io.requery.ManyToMany;
import io.requery.Persistable;
import java.util.List;
import java.util.Set;

@Entity
public interface Specialty extends Observable, Parcelable, Persistable {

    @Key
    int getId();

    @ManyToMany
    Set<Employee> getEmployeeList();

    @NonNull
    @Bindable
    String getName();
    void setName(String name);
}