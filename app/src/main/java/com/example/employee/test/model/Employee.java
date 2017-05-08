package com.example.employee.test.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.JunctionTable;
import io.requery.Key;
import io.requery.ManyToMany;
import io.requery.Persistable;
import java.util.Date;
import java.util.List;

@Entity
public interface Employee extends Observable, Parcelable, Persistable {

    @Key
    @Generated
    int getId();

    @NonNull
    @Bindable
    String getFirst();

    @NonNull
    @Bindable
    String getLast();

    @Bindable
    Date getBirthday();

    @NonNull
    @Bindable
    String getImage();

    @Bindable
    String getAge();

    @JunctionTable(type = SpecialtyItem.class)
    @ManyToMany
    List<Specialty> getSpecialtyList();
}
