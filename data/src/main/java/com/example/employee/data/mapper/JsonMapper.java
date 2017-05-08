package com.example.employee.data.mapper;

import com.example.employee.data.cache.Serializer.DataDeserializer;
import com.example.employee.data.entity.Employee;
import com.example.employee.data.entity.EmployeeList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Мапинг для Json
 */
public class JsonMapper {

    private static volatile JsonMapper instance;

    public static JsonMapper getInstance() {
        JsonMapper localInstance = instance;
        if (localInstance == null) {
            synchronized (JsonMapper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new JsonMapper();
                }
            }
        }
        return localInstance;
    }

    private final Gson gson=new GsonBuilder().registerTypeAdapter(Date.class, new DataDeserializer()).create();

    /**
     * Получение списка работников из ответа сервера
     * @param jsonResponse
     * @return
     * @throws JsonSyntaxException
     */
    public List<Employee> transform(String jsonResponse)
            throws JsonSyntaxException {
        Type type = new TypeToken<EmployeeList>() {}.getType();
        EmployeeList list = this.gson.fromJson(jsonResponse, type);
        return list.getEmployees();
    }


}
