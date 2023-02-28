package com.example.education;

public class NotificationTeacherClass {

    String name,city,teacherClass,image,phone,material,perHour,hour,status,time,goodDate,id,idStudent,statusStudent,rating,evaluation;

    public NotificationTeacherClass(String name, String city, String teacherClass, String image, String phone,String evaluation, String material, String perHour, String hour, String status, String time, String goodDate, String id, String idStudent, String statusStudent, String rating) {
        this.name = name;
        this.city = city;
        this.teacherClass = teacherClass;
        this.image = image;
        this.phone = phone;
        this.material = material;
        this.perHour = perHour;
        this.hour = hour;
        this.status = status;
        this.time = time;
        this.goodDate = goodDate;
        this.id = id;
        this.idStudent = idStudent;
        this.statusStudent = statusStudent;
        this.rating = rating;
        this.evaluation = evaluation;
    }



    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public NotificationTeacherClass(){

    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGoodDate() {
        return goodDate;
    }

    public void setGoodDate(String goodDate) {
        this.goodDate = goodDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPerHour() {
        return perHour;
    }

    public void setPerHour(String perHour) {
        this.perHour = perHour;
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

    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
