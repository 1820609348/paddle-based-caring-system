package com.example.myapp.utils;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetApis {


    /**
     *【GET请求03】
     * params传参(不封装入参)的GET:
     * 请求接口:/testApi/GETApi/test_login?userName=201826702111&userPwd=123456
     */
    public Map<String,Object> Get_login_responsecode(String userName, String userPwd){
        SHAUtils shaUtils = new SHAUtils();
        String userPwd_SHA512 = shaUtils.SHA512(userPwd);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(IP_URL+"/testApi/GETApi/test_login?userName="+userName+"&userPwd="+userPwd_SHA512)
                .build();
        try {
            Response response = client.newCall(request).execute();//创建回调
            String result = response.body().string();//接收请求的返回数据
                                                    // 处理返回的数据
            Gson gson = new Gson();
            Map<String,Object> resultMap = gson.fromJson(result,Map.class);//使用Gson将json格式的字符串转换为HashMap
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    public Map<String,Object> Get_register_responsecode(String userName, String userPwd){
        SHAUtils shaUtils = new SHAUtils();
        String userPwd_SHA512 = shaUtils.SHA512(userPwd);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url((IP_URL+"/testApi/GETApi/test_register?userName="+userName+"&userPwd="+userPwd_SHA512))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            Gson gson = new Gson();
            Map<String,Object> resultMap = gson.fromJson(result,Map.class);
            return resultMap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public Map<String,Object> Get_informationchange_responsecode(String userName, String name,String sex,int age,String community,String address,String phonenumber,String wechatnumber,String isalways){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url((IP_URL+"/testApi/GETApi/test_informationchange?userName="+userName+"&residentName="+name+"&sex="+sex+"&age="+age+"&community="+community+"&address="+address+"&phonenumber="+phonenumber+"&wechatnumber="+wechatnumber+"&isalways="+isalways))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            Gson gson = new Gson();
            Map<String,Object> resultMap = gson.fromJson(result,Map.class);
            return resultMap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }//Get_informationread_responsecode

    public Map<String,Object> Get_informationread_responsecode(String userName){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url((IP_URL+"/testApi/GETApi/test_informationread?userName="+userName))
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            Gson gson = new Gson();
            Map<String,Object> resultMap = gson.fromJson(result,Map.class);
            return resultMap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
