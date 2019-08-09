package com.example.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

public class GsonUtil {

    public static final String toJson(Object o){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));
        return builder.create().toJson(o);
    }

    public static final <T> T fromJson(String jsonStr, Class<T> classOfT){
        return new Gson().fromJson(jsonStr, classOfT);
    }
    /**
     * 将object转成对象
     * @author Yuzhuting
     * @date 2018/6/5 10:12
     * @param object, classOfT
     * @return T
     **/
    public static <T> T convertObject(Object object, Class<T> classOfT){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));
        Gson gson = builder.create();
        return  gson.fromJson(gson.toJson(object), classOfT);
    }
}
