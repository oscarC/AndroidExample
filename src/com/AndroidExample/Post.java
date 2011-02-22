package com.AndroidExample;

import android.graphics.Bitmap;

public class Post {

    String message;
    String time;
    String username;
    Bitmap icon;

    public String getMessage(){
        return message;
    }

    public String getUsername(){
        return username;
    }

    public Bitmap getIcon(){
        return icon;
    }

    public void setUsername(String un){
        this.username = un;
    }

    public void setMessage(String msg){
        this.message = msg;
    }

    public void setIcon(Bitmap icon){
        this.icon = icon;
    }
}