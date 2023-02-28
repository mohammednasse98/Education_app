package com.example.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(1600, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                Intent intent=new Intent(MainActivity.this,LogIn.class);
                startActivity(intent);
            }
        }.start();

    }

}