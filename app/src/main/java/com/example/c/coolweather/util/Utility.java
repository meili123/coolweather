package com.example.c.coolweather.util;

import android.text.TextUtils;

import com.example.c.coolweather.db.City;
import com.example.c.coolweather.db.County;
import com.example.c.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by C on 2017/9/15.
 */

public class Utility {
    /**
     * 解析的和处理服务器返回的省级数据
     */
    public  static boolean handleProvinceResponse(String reponse){
        if (!TextUtils.isEmpty(reponse)){
            try{
//                接收道返回省的数据
                JSONArray allProvinces=new JSONArray(reponse);
                for(int i=0;i<allProvinces.length();i++){
//                    获取一个对象
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProviceName(provinceObject.getString("name"));
                    province.setProviceCode(provinceObject.getInt("id"));
//                    存储数据
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析的和处理服务器返回的市级数据
     */
    public  static boolean handleCityResponse(String reponse,int proviceId){
        if (!TextUtils.isEmpty(reponse)){
            try {
//                接收返回市的数据
                JSONArray allCity=new JSONArray(reponse);
                for (int i=0;i<allCity.length();i++){
                    JSONObject cityObject=allCity.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProviceId(proviceId);
                    city.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析的和处理服务器返回的县级数据
     */
    public  static boolean handleCountyResponse(String reponse,int cityId){
        if (!TextUtils.isEmpty(reponse)){
            try {
//                接收返回市的数据
                JSONArray allCounty=new JSONArray(reponse);
                for (int i=0;i<allCounty.length();i++){
                    JSONObject countyObject=allCounty.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();

                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
}
