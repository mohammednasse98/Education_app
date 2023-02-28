package com.example.education;


public class Student {
    private String Name,Password,Phone,Gender,Choose,Date,City,image,email;

    public Student() {

    }

    public Student(String name, String password,String email ,String phone, String gender, String choose, String date, String city,String image) {
        Name = name;
        Password = password;
        Phone = phone;
        Gender = gender;
        Choose = choose;
        Date = date;
        City = city;
        this.image = image;
        this.email = email;

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

    public String getChoose() {
        return Choose;
    }

    public void setChoose(String choose) {
        Choose = choose;
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
}