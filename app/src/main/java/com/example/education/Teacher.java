package com.example.education;

public class Teacher {
    private String Name, Password, Phone, Gender, choose, Date, City, TeacherClass, image, email,evaluation;


    public Teacher() {

    }

    public Teacher(String name, String password, String phone, String email,String evaluation, String gender, String choose, String date, String city, String teacherClass,  String image) {
        Name = name;
        Password = password;
        Phone = phone;
        Gender = gender;
        this.choose = choose;
        Date = date;
        City = city;
        TeacherClass = teacherClass;
        this.image = image;
        this.email = email;
        this.evaluation = evaluation;

    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTeacherClass() {
        return TeacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        TeacherClass = teacherClass;
    }

}