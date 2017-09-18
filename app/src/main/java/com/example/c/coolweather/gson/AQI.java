package com.example.c.coolweather.gson;

/**
 * Created by C on 2017/9/18.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
