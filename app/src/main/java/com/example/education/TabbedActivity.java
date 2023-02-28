package com.example.education;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.education.ui.main.SectionsPagerAdapter;

public class TabbedActivity extends AppCompatActivity {
    String ss="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
            TextView textView=findViewById(R.id.title);
        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String phone= intent.getStringExtra("phone");
        String gender= intent.getStringExtra("gender");
        String city=intent.getStringExtra("city");
        String date=intent.getStringExtra("date");
        String password=intent.getStringExtra("password");
        String image=intent.getStringExtra("image");
        ss=name+"/t"+phone+"/t"+gender+"/t"+city+"/t"+" "+"/t"+date+"/t"+password+"/t"+image;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),ss);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        textView.setText(name);
        }



    }
