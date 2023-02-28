package com.example.education;

public class pushValue {

    String material;
    String phone;
    String name;
    String city;
    String date;
    String image;
    String hour;
    String status;
    String statusStudent;
    String key;
    String id,idStudent;

    String evaluation;

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public pushValue(String material, String phone, String name,  String evaluation,String city, String date, String image, String hour, String status, String statusStudent, String key, String id, String idStudent) {
        this.material = material;
        this.phone = phone;
        this.name = name;
        this.city = city;
        this.date = date;
        this.image = image;
        this.hour = hour;
        this.status = status;
        this.statusStudent = statusStudent;
        this.key = key;
        this.id = id;
        this.idStudent = idStudent;
        this.evaluation = evaluation;
    }

    public pushValue() {

    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getStatusStudent() {
        return statusStudent;
    }

    public void setStatusStudent(String statusStudent) {
        this.statusStudent = statusStudent;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void add(pushValue config) {
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
