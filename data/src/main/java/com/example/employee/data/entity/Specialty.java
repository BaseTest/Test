package com.example.employee.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Должность
 */
@NoArgsConstructor
public class Specialty extends BaseEntyty {
    @Getter
    @Setter
    //specialty_id
    private String specialty_id;
    @Getter
    @Setter
    //name
    private String name;
}
