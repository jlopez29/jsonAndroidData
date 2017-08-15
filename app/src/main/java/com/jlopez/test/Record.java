package com.jlopez.test;

import android.graphics.Bitmap;

/**
 * Created by JLOPEZ on 8/15/2017.
 */

public class Record {
    private String id;
    private String name;
    private String phone;
    private String date;
    private String age;
    private String image;

    Record(String id, String name, String phone, String date, String age, String image)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.age = age;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
