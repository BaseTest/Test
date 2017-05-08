package com.example.employee.data.net;

import android.support.annotation.Nullable;
import com.example.employee.data.mapper.JsonMapper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

/**
 * Api для соединения с сервером
 */
public class ConnectionApi {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private URL url;
    private String response;

    private ConnectionApi(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ConnectionApi createGET(String url) throws MalformedURLException {
        return new ConnectionApi(url);
    }


    public <T> T requestSyncCall() throws IOException {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();
        return getResponseBody(okHttpClient.newCall(request).execute());
    }

    private <T> T getResponseBody(Response response) throws IOException {
        return (T) JsonMapper.getInstance().transform(response.body().string());
    }

    @Nullable
    public void requestAsyncCall(@NonNull IRestResponse restResponse) {
        connectToApi(restResponse);
    }

    private void connectToApi(@NonNull final IRestResponse restResponse) {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                restResponse.fail(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful())
                    restResponse.fail(new Exception(String.format("Ошибка сервера %s", response.code())));
                else {
                    restResponse.success(getResponseBody(response));
                    ConnectionApi.this.response = response.body().string();
                }
            }
        });
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }
}
