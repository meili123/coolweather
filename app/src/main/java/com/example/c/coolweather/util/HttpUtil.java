package com.example.c.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by C on 2017/9/15.
 */

public class HttpUtil  {
    public static void sendOkHttpRequest(String adress,okhttp3.Callback callback){

//        OkHttpClient client=new OkHttpClient();
//        Request request=new Request.Builder().url("http://192.168.90.73/getdata.json").build();






//        创建请求网络客户
        OkHttpClient client=new OkHttpClient();
//        请求地址
        Request request=new Request.Builder().url(adress).build();
//        设置客户请求地址队列
        client.newCall(request).enqueue(callback);
    }
}
