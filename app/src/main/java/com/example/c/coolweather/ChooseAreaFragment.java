package com.example.c.coolweather;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c.coolweather.db.City;
import com.example.c.coolweather.db.County;
import com.example.c.coolweather.db.Province;
import com.example.c.coolweather.util.Utility;
import com.example.c.coolweather.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by C on 2017/9/15.
 */

public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CITY=1;
    public static final int LEVEL_COUNTY=2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
//    数据源
    private List<String> dataList=new ArrayList<>();
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县列表
     */
    private List<County> countyList;
    /**
     * 选中省份
     */
    private Province selectedProvince;
    /**
     * 选中城市
     */
    private City selectedCity;
    /**
     * 选中的级别
     */
    private int currentLevel;

//    获取控件实例

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.choose_area,container,false);
        titleText=(TextView)view.findViewById(R.id.title_text);
        backButton=(Button)view.findViewById(R.id.backButton);

        sendRequsetwithOkHttp();
        listView=(ListView)view.findViewById(R.id.list_view);
        adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"真美",Toast.LENGTH_SHORT).show();

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"真美",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState){
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                if (currentLevel==LEVEL_PROVINCE){
////                    selectedProvince=provinceList.get(i);
////                    queryCities();
////                }else if(currentLevel==LEVEL_CITY){
////                    selectedCity=cityList.get(i);
////                    queryCounties();
////                }
//                Toast.makeText(getContext(),"真美",Toast.LENGTH_SHORT).show();
//            }
//        });
//        backButton.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
////                if (currentLevel==LEVEL_COUNTY){
////                    queryCities();
////                }else if (currentLevel==LEVEL_CITY){
////                    queryProvinces();
////                }
//
//            Toast.makeText(getContext(),"真美",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }






    //    数据请求方式
    private void sendRequsetwithOkHttp(){
        showProgressDialog();
        //        设置头标题
        titleText.setText("中国");
//        隐藏返回按钮
//        backButton.setVisibility(View.GONE);
        //        读取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http:guolin.tech/api/china").build();

                    Response response=client.newCall(request).execute();
                    String reponseData=response.body().string();
//                    Json解析方式
                    parseJsonWithObject(reponseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private  void   parseJsonWithObject(String jsonData){
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                dataList.add(name);
                closeProgressDialog();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void sendRequsetwithOkHttp1(){
        //        读取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http:guolin.tech/api/china/13").build();

                    Response response=client.newCall(request).execute();
                    String reponseData=response.body().string();
//                    Json解析方式
                    parseJsonWithObject1(reponseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private  void   parseJsonWithObject1(String jsonData){


        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
//
                dataList.add(name);

                closeProgressDialog();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    //    /**
//     * 显示进度条对话框
//     */
    private void showProgressDialog(){
        if (progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }
    /**
     * 关闭对话框
     */
    private void closeProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }













































//
//    /**
//     * 查询全国所有的省，优先从数据库查询，如果没有查询到在去服务器查询
//     */
//    private void queryProvinces(){
////        设置头标题
//        titleText.setText("中国");
////        隐藏返回按钮
//        backButton.setVisibility(View.GONE);
////        读取数据
//        provinceList= DataSupport.findAll(Province.class);
//        if (provinceList.size()>0){
//            dataList.clear();
//            for (Province province:provinceList){
//                dataList.add(province.getProviceName());
//            }
//            adapter.notifyDataSetChanged();
//            listView.setSelection(0);
//            currentLevel=LEVEL_PROVINCE;
//
//        }else {
////            没有数据就从服务器上查询
//            String adress = "http:guolin.tech/api/china";
//            queryFromServer(adress);
//        }
//
//    }
//
//    /**
//     * 查询省内所有的市，优先从数据库查询，如果没有再到服务器上查询
//     */
//    private void queryCities(){
//        titleText.setText(selectedProvince.getProviceName());
//        backButton.setVisibility(View.VISIBLE);
//        cityList=DataSupport.where("id=?", String.valueOf(selectedProvince.getId())).find(City.class);
//        if (cityList.size()>0){
//            dataList.clear();
//            for (City city:cityList){
//                dataList.add(city.getCityName());
//            }
//            adapter.notifyDataSetChanged();
//            listView.setSelection(0);
//            currentLevel=LEVEL_CITY;
//        }else {
//            int provinceCode=selectedProvince.getProviceCode();
//            String address="http://guolin.tech/api/china/"+provinceCode;
//            queryFromServer(address,"name");
//        }
//    }
//    /**
//     * 查询选中市内所有的县，优先从数据库查询，若没有查询到从服务上查询
//     */
//    private void  queryCounties(){
//        titleText.setText(selectedCity.getCityName());
//        backButton.setVisibility(View.VISIBLE);
//        countyList=DataSupport.where("id=",String.valueOf(selectedCity.getId())).find(County.class);
//        if (countyList.size()>0){
//            dataList.clear();
//            for (County county:countyList){
//                dataList.add(county.getCountyName());
//            }
//            adapter.notifyDataSetChanged();
//            listView.setSelection(0);
//            currentLevel=LEVEL_COUNTY;
//        }else {
//            int provinceCode=selectedProvince.getProviceCode();
//            int cityCode=selectedCity.getCityCode();
//            String adress="http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
//            queryFromServer(adress,"name");
//        }
//    }
//    /**
//     * 根据传入的地址和类型从服务器上查询市县数据
//     */
//    private void queryFromServer(String adress,final String type){
////        显示进度条
//        showProgressDialog();
////        向服务器发送请求
//        HttpUtil.sendOkHttpRequest(adress, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //通过runOnUiThread方法回到主线处理逻辑
//                getActivity().runOnUiThread(new Runnable() {
//                    @RequiresApi(api = Build.VERSION_CODES.M)
//                    @Override
//                    public void run() {
//                        closeProgressDialog();
//                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
////            获得返回的数据
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//            String reponseText=response.body().string();
////            返回的数据
//                boolean result=false;
//
//                if ("name".equals(type)){
////                    处理返回的数据
//                    result=Utility.handleProvinceResponse(reponseText);
//                }else if ("name".equals(type)){
//                    result=Utility.handleCityResponse(reponseText,selectedProvince.getId());
//                }else if ("name".equals(type)){
//                    result= Utility.handleCountyResponse(reponseText,selectedCity.getId());
//                }
//                if (result){
////                    牵涉到UI操作，必须在主线程中调用，子线程切换到主线程
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            closeProgressDialog();
//                            if ("name".equals(type)){
//                                queryProvinces();
//                            }else if("name".equals(type)){
//                                queryCities();
//                            }else if ("name".equals(type)){
//                                queryCounties();
//                            }
//                        }
//                    });
//                }
//            }
//        });
//
//    }
//

}
