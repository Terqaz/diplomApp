package com.example.ddz1.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Simplex48Api {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    protected static HttpUrl.Builder getGeneralUrlBuilder() {
        return new HttpUrl.Builder().scheme("https")
                .host("testapi.simplex48.ru")
                .addPathSegments("api/Web");
    }

    protected static <T> T executeRequest(Request request, TypeToken<T> type) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            else {
                return gson.fromJson(response.body().string(), type.getType());
            }
        }
    }
}
