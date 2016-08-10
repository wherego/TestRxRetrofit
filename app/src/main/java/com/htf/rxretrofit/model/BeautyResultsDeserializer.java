package com.htf.rxretrofit.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * 2016/7/21 10:29
 * Author: htf
 */
public class BeautyResultsDeserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // 转换Json的数据, 获取内部有用的信息
//        JsonElement results = json.getAsJsonObject().get("data")
//                .getAsJsonObject().get("results");
        return new Gson().fromJson(json, typeOfT);
    }
}
