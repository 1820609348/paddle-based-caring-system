package com.example.myapp.entity;

public class DoctorEntity {
    public String getDotcor_name() {
        return dotcor_name;
    }

    public void setDotcor_name(String dotcor_name) {
        this.dotcor_name = dotcor_name;
    }

    public String getDoctor_username() {
        return doctor_username;
    }

    public void setDoctor_username(String doctor_username) {
        this.doctor_username = doctor_username;
    }

    public String getDoctor_content() {
        return doctor_content;
    }

    public void setDoctor_content(String doctor_content) {
        this.doctor_content = doctor_content;
    }

    public DoctorEntity(){

    }

    public DoctorEntity(String doctor_content,String dotcor_name,String dotcor_username,int aIcon){
        this.doctor_content = doctor_content;
        this.doctor_username = dotcor_username;
        this.dotcor_name = dotcor_name;
        this.aIcon = aIcon;
    }
    public DoctorEntity(String doctor_content,String dotcor_name,String dotcor_username,int aIcon,String time){
        this.doctor_content = doctor_content;
        this.doctor_username = dotcor_username;
        this.dotcor_name = dotcor_name;
        this.aIcon = aIcon;
        this.time = time;
    }
    private String dotcor_name;
    private String doctor_username;
    private String doctor_content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }

    private int aIcon;

}
