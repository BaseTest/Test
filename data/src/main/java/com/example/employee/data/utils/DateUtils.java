package com.example.employee.data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 * Утилитный класс для работы с датами
 */
public class DateUtils {
    // TODO вынести в фабрику форматов
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String getDate() {
        return dateFormat.format(new Date());
    }


    public static String toString(Date value) {
        return value == null ? "" : dateFormat.format(value);
    }

    public static String getAge(Date birthday) {
        if (birthday == null)
            return null;
        return Integer.toString(calculateAge(new LocalDate(birthday.getTime())));
    }

    public static int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Years.yearsBetween(birthDate, new LocalDate()).getYears();
        } else {
            return 0;
        }
    }
}
