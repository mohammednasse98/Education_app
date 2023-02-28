package com.example.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class EditActivityTe extends AppCompatActivity {
    String phone="";
    String password1="";
    String city="";
    String name="";
    String gender="";
    String date="";
    String material="";
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
        material=intent.getStringExtra("teacherClass");
        if(num.equals("1")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("name",name);
            bundle1.putString("teacherClass",material);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditNameTe fragmentEditNameTe = new FragmentEditNameTe();
            fragmentEditNameTe.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditNameTe);
            fragmentTransaction.commit();
        }else if(num.equals("2")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("password",password1);
            bundle1.putString("teacherClass",material);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditPasswordTe fragmentEditPasswordTe = new FragmentEditPasswordTe();
            fragmentEditPasswordTe.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditPasswordTe);
            fragmentTransaction.commit();
        }else if(num.equals("3")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("gender",gender);
            bundle1.putString("teacherClass",material);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditGenderTe fragmentEditGenderTe = new FragmentEditGenderTe();
            fragmentEditGenderTe.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditGenderTe);
            fragmentTransaction.commit();
        }else  if(num.equals("4")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("city",city);
            bundle1.putString("teacherClass",material);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditCityTe fragmentEditCityTe = new FragmentEditCityTe();
            fragmentEditCityTe.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditCityTe);
            fragmentTransaction.commit();
        }else if(num.equals("5")){
            Bundle bundle1=new Bundle();
            bundle1.putString("phone",phone);
            bundle1.putString("date",date);
            bundle1.putString("teacherClass",material);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentEditDateTe fragmentEditDateTe = new FragmentEditDateTe();
            fragmentEditDateTe.setArguments(bundle1);
            fragmentTransaction.replace(R.id.frag1, fragmentEditDateTe);
            fragmentTransaction.commit();
        }

    }
}