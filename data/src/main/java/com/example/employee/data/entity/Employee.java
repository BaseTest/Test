package com.example.employee.data.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Работник
 */
@NoArgsConstructor
public class Employee extends BaseEntyty {

    @Getter
    @Setter
    @SerializedName("f_name")
    private String firstName;
    @Getter
    @Setter
    @SerializedName("l_name")
    private String secondName;
    @Getter
    @Setter
    @SerializedName("birthday")
    private Date birthday;
    @Getter
    @Setter
    @SerializedName("avatr_url")
    private String url;
    @Getter
    @Setter
    @SerializedName("specialty")
    private List<Specialty> specialtys;}
