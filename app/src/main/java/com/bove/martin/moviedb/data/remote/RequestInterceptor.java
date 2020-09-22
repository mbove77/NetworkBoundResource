package com.bove.martin.moviedb.data.remote;

import com.bove.martin.moviedb.commons.ApiKey;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Martín Bove on 03-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();

        HttpUrl newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", ApiKey.API_KEY)
                .build();
        Request newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build();

        return chain.proceed(newRequest);
    }
}
