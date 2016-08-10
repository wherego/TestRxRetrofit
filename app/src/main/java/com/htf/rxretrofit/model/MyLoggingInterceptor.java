package com.htf.rxretrofit.model;

import com.htf.rxretrofit.utils.Loger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 2016/7/21 10:03
 * Author: htf
 */
public class MyLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        Loger.d("response====>" + response.body().string());
        return response;
    }
}
