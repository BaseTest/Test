package com.example.employee.test.model;

import android.databinding.Observable;
import android.os.Parcelable;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;
import io.requery.ReferentialAction;

@Entity
public interface SpecialtyItem extends Observable, Parcelable, Persistable {

    @Key
    @Generated
    int getId();

    @ForeignKey(references = Employee.class)
    int getEmployeeId();

    @ForeignKey(references = Specialty.class)
    int getSpecialtyId();
}
