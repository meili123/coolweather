package com.example.c.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by C on 2017/9/14.
 */

public class Province extends DataSupport {
    private int id;
    private String proviceName;
//    省的代号
    private  int proviceCode;
    public int getId(){
        return id;
    }
    public String getProviceName(){
        return proviceName;
    }
    public int getProviceCode(){
        return proviceCode;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setProviceName(String proviceName){
        this.proviceName=proviceName;
    }
    public void setProviceCode(int proviceCode){
        this.proviceCode=proviceCode;
    }



}
