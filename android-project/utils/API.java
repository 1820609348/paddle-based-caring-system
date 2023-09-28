package com.example.myapp.utils;

public class API {
    public  String getScore(String feature){
        String api = null;
        switch (feature){
            case "普通话":
                api = "1537";
                break;
            case "粤语":
                api = "1637";
                break;
            case "四川话":
                api = "1837";
                break;
        }
        return api;
    }
}
