package com.example.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class EditActivity extends AppCompatActivity {
        String phone="";
        String password1="";
        String city="";
        String name="";
        String gender="";
        String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent=getIntent();
        String num=intent.getStringExtra("Num");
        phone=intent.getStringExtra("phone");
        password1=intent.getStringExtra("password");
        city=intent.getStringExtra("city");
        name=intent.getStringExtra("name");
        gender=intent.getStringExtra("gender");
        date=intent.getStringExtra("date");
        if(num.equals("1")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("name",name);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditName fragmentEditName = new FragmentEditName();
            fragmentEditName.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditName);
            fragmentTransaction.commit();
        }else if(num.equals("2")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("password",password1);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditPassword fragmentEditPassword = new FragmentEditPassword();
            fragmentEditPassword.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditPassword);
            fragmentTransaction.commit();
        }else if(num.equals("3")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("gender",gender);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditGender fragmentEditGender = new FragmentEditGender();
            fragmentEditGender.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditGender);
            fragmentTransaction.commit();
        }else  if(num.equals("4")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("city",city);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditCity fragmentEditCity = new FragmentEditCity();
            fragmentEditCity.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditCity);
            fragmentTransaction.commit();
        }else if(num.equals("5")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("date",date);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditDate fragmentEditDate = new FragmentEditDate();
            fragmentEditDate.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditDate);
            fragmentTransaction.commit();
        }

    }
}