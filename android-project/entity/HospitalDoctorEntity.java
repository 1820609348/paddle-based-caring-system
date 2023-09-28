package com.example.myapp.entity;

public class HospitalDoctorEntity {

    private String dotcor_name;
    private String doctor_content;
    private String doctor_yiyuan;
    private String doctor_keshi;
    private String doctor_zhiwu;
    private int aIcon;

    public HospitalDoctorEntity(){

    }

    public HospitalDoctorEntity(String dotcor_name, String doctor_content, String doctor_yiyuan, String doctor_keshi, String doctor_zhiwu, int aIcon) {
        this.dotcor_name = dotcor_name;
        this.doctor_content = doctor_content;
        this.doctor_yiyuan = doctor_yiyuan;
        this.doctor_keshi = doctor_keshi;
        this.doctor_zhiwu = doctor_zhiwu;
        this.aIcon = aIcon;
    }


    public String getDotcor_name() {
        return dotcor_name;
    }

    public void setDotcor_name(String dotcor_name) {
        this.dotcor_name = dotcor_name;
    }

    public String getDoctor_content() {
        return doctor_content;
    }

    public void setDoctor_content(String doctor_content) {
        this.doctor_content = doctor_content;
    }

    public String getDoctor_yiyuan() {
        return doctor_yiyuan;
    }

    public void setDoctor_yiyuan(String doctor_yiyuan) {
        this.doctor_yiyuan = doctor_yiyuan;
    }

    public String getDoctor_keshi() {
        return doctor_keshi;
    }

    public void setDoctor_keshi(String doctor_keshi) {
        this.doctor_keshi = doctor_keshi;
    }

    public String getDoctor_zhiwu() {
        return doctor_zhiwu;
    }

    public void setDoctor_zhiwu(String doctor_zhiwu) {
        this.doctor_zhiwu = doctor_zhiwu;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }

}
