package com.htf.rxretrofit.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htf.retrofit.net.api.GankService;
import com.htf.rxretrofit.bean.Beauties;
import com.htf.rxretrofit.imodel.IBeautyModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 2016/7/21 10:01
 * Author: htf
 */
public class BeautyModel implements IBeautyModel {
    
    private final GankService gankService;
    
    private static BeautyModel beautyModel;
    
    public static synchronized BeautyModel getInstance() {
        if(beautyModel == null) {
            beautyModel = new BeautyModel();
        }
        return beautyModel;
    }

    private BeautyModel() {
        /* 可以通过 setLevel 改变日志级别
            共包含四个级别：NONE、BASIC、HEADER、BODY
        
            NONE 不记录
            
            BASIC 请求/响应行
            --> POST /greeting HTTP/1.1 (3-byte body)
            <-- HTTP/1.1 200 OK (22ms, 6-byte body)
            
            HEADER 请求/响应行 + 头
            
            --> Host: example.com
            Content-Type: plain/text
            Content-Length: 3
            
            <-- HTTP/1.1 200 OK (22ms)
            Content-Type: plain/text
            Content-Length: 6
            
            BODY 请求/响应行 + 头 + 体
        */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        // json过滤
//        Gson customGsonInstance = new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<Beauties>() {
//                        }.getType(), new BeautyResultsDeserializer<Beauties>())
//                .create();

        Gson gson = new GsonBuilder().setDateFormat(GankService.GANK_DATA_FORMAT).create();

        // 适配器
        Retrofit marvelApiAdapter = new Retrofit.Builder()
                .baseUrl(GankService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        // 服务
        gankService = marvelApiAdapter.create(GankService.class);
    }

    @Override
    public Observable<Beauties> getBeauty(int size, int page) {
        return gankService.getData(size, page);
    }
}
