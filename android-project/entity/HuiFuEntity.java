package com.example.myapp.entity;

public class HuiFuEntity {
    private String huifuduixiang_usernaem;
    private String huifufankui_title;
    private String huifufankui_content;

    public HuiFuEntity(){

    }
    public HuiFuEntity(String huifuduixiang_usernaem,String huifufankui_title,String huifufankui_content,int aIcon){
        this.huifuduixiang_usernaem = huifuduixiang_usernaem;
        this.huifufankui_title = huifufankui_title;
        this.huifufankui_content = huifufankui_content;
        this.aIcon  = aIcon;
    }

    public String getHuifuduixiang_usernaem() {
        return huifuduixiang_usernaem;
    }

    public void setHuifuduixiang_usernaem(String huifuduixiang_usernaem) {
        this.huifuduixiang_usernaem = huifuduixiang_usernaem;
    }

    public String getHuifufankui_title() {
        return huifufankui_title;
    }

    public void setHuifufankui_title(String huifufankui_title) {
        this.huifufankui_title = huifufankui_title;
    }

    public String getHuifufankui_content() {
        return huifufankui_content;
    }

    public void setHuifufankui_content(String huifufankui_content) {
        this.huifufankui_content = huifufankui_content;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }

    private int aIcon;


}