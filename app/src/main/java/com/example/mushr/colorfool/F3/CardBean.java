package com.example.mushr.colorfool.F3;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class CardBean implements Serializable {
    public String imageId;      //  图片id
    public Drawable image;      //  图片
    public String userId;       //  用户id
    public String userName;     //  用户名
    //  ... 其他属性

    public CardBean(){}
    public CardBean(String imageId, Drawable img, String userId, String userName) {
        this.imageId = imageId;
        this.image = img;
        this.userId = userId;
        this.userName = userName;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
