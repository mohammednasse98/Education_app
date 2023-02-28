package com.example.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSettingTe extends Fragment {
    TextView name,password,city,gender,date;
    RelativeLayout nameRelative,passwordRelative,cityRelative,genderRelative,dateRelative;
    Button button;
    String phone="";
    String password1="";
    String name1="";
    String city1="";
    String gender1="";
    String date1="";
    String material="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.fragment_sittings,container,false);
        button=view.findViewById(R.id.logOut);
        name=view.findViewById(R.id.name1);
        password=view.findViewById(R.id.password1);
        city=view.findViewById(R.id.city1);
        gender=view.findViewById(R.id.gender1);
        date=view.findViewById(R.id.date1);
        Bundle bundle=getArguments();
        name.setText(bundle.getString("name"));
        city.setText(bundle.getString("city"));
        gender.setText(bundle.getString("gender"));
        date.setText(bundle.getString("date"));
        phone=bundle.getString("phone");
        password1=bundle.getString("password");
        city1=bundle.getString("city");
        name1=bundle.getString("name");
        gender1=bundle.getString("gender");
        material=bundle.getString("teacherClass");
        date1=bundle.getString("date");
        nameRelative=view.findViewById(R.id.nameRelative);
        passwordRelative=view.findViewById(R.id.passwordRelative);
        cityRelative=view.findViewById(R.id.cityRelative);
        genderRelative=view.findViewById(R.id.genderRelative);
        dateRelative=view.findViewById(R.id.dateRelative);
        nameRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),EditActivityTe.class);
                intent.putExtra("phone",phone);
                intent.putExtra("Num","1");
                intent.putExtra("name",name1);
                intent.putExtra("teacherClass",material);
                startActivity(intent);


            }
        });
        passwordRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),EditActivityTe.class);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password1);
                intent.putExtra("Num","2");
                intent.putExtra("teacherClass",material);
                startActivity(intent);


            }
        });
        genderRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditActivityTe.class);
                intent.putExtra("phone",phone);
                intent.putExtra("Num","3");
                intent.putExtra("gender",gender1);
                intent.putExtra("teacherClass",material);
                startActivity(intent);
            }
        });
        cityRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditActivityTe.class);
                intent.putExtra("phone",phone);
                intent.putExtra("Num","4");
                intent.putExtra("city",city1);
                intent.putExtra("teacherClass",material);
                startActivity(intent);
            }
        });

        dateRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditActivityTe.class);
                intent.putExtra("phone",phone);
                intent.putExtra("Num","5");
                intent.putExtra("date",date1);
                intent.putExtra("teacherClass",material);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LogIn.class));
            }
        });
        return view;
    }}