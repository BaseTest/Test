package com.example.employee.data.net;

/**
 * Created by Alchemist on 29.04.2017.
 */

public interface IRestResponse<T> {
    public void success(T value);

    public void fail(Throwable e);
}
