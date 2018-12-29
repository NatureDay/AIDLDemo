package com.example.testdemo;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * json工具类
 */

public class JsonUtil {

    private static final Gson sGson = new Gson();

    /**
     * 对象转json字符串
     *
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        try {
            return sGson.toJson(src);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
