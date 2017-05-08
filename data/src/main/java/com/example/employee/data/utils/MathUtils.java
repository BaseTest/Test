package com.example.employee.data.utils;

import android.text.TextUtils;
import com.example.employee.data.exception.ExceptionHandler;

/**
 * Created by Alchemist on 03.05.2017.
 */

public class MathUtils {
    public static Integer toInt(String value) {
        if (TextUtils.isEmpty(value))
            return null;
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            ExceptionHandler.handle(e, "Не удалось преобразвоать строку в Integer");
        }
        return null;
    }
}
