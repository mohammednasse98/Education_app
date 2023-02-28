package com.example.education;

import java.util.List;
import java.util.Map;

public class Model {
    String name;
    String phone;
    String teacherClass;
    String city;
    String evaluation;
    String date;
    String image;
    String material;
    Model(){

    }
    public Model(String name, String phone, String teacherClass, String city, String evaluation, String date,String image, String material) {
        this.name = name;
        this.phone = phone;
        this.teacherClass = teacherClass;
        this.city = city;
        this.evaluation = evaluation;
        this.date = date;
        this.image=image;
        this.material=material;
    }



    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
