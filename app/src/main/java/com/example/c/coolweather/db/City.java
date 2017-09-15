package com.example.c.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by C on 2017/9/14.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int proviceId;
   public int getId(){
       return id;
   }
    public String getCityName(){
        return cityName;
    }
    public int getCityCode(){
        return cityCode;
    }
    public int getProviceId(){
        return proviceId;
    }


    public void setId(int id){
        this.id=id;
    }
    public void  setCityName(String cityName){
        this.cityName=cityName;
    }
    public void setCityCode(int cityCode){
        this.cityCode=cityCode;
    }
    public void setProviceId(int proviceId){
        this.proviceId=proviceId;
    }




}
