package com.example.myapp.entity;

public class TongyongEntity {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String title;
    private String content;

    public TongyongEntity(){

    }

    public TongyongEntity(String title,String content){
        this.content = content;
        this.title = title;
    }

}
