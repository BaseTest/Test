package com.example.employee.data.cache.Serializer;

import android.text.TextUtils;
import com.example.employee.data.adapter.HashMapAdapter;
import com.example.employee.data.exception.ExceptionHandler;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Десерелизатор даты
 */

public class DataDeserializer<T extends Date> implements JsonDeserializer<T> {

    private static String dateMatch = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$";
    private static String dateReverceMatch = "^[0-3][0-9]-[0-1][0-9]-[0-9]{4}$";

    private static final HashMap<String, String> DATE_FORMATS =
            new HashMapAdapter<String, String>()
                    .append(dateMatch, "yyyy-MM-dd")
                    .append(dateReverceMatch, "dd-MM-yyyy")
                    .get();


    @Override
    public T deserialize(JsonElement jsonElement, Type typeOF,
                         JsonDeserializationContext context) throws JsonParseException {
        String dateStr = jsonElement.getAsString();
        if (TextUtils.isEmpty(dateStr))
            return null;
        for (Map.Entry<String, String> format : DATE_FORMATS.entrySet()) {
            try {
                if (dateStr.matches(format.getKey()))
                    return (T) new SimpleDateFormat(format.getValue(), Locale.US).parse(dateStr);
            } catch (ParseException e) {
                ExceptionHandler.handle(e, String.format("Формат %s неприменим к дате %s", format, dateStr));
            }
        }
        throw new JsonParseException("Неизвестный формат даты");
    }
}

