package com.example.education;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class FragmentEditDate extends Fragment {
        View view;
    private DatabaseReference mDatabase;
    private Firebase mRootRef;
    Button button,cancel;
    TextView textView;
    TextView textView1;
    int years ;
    String string="";
    String phone="";
    String date="";

    DatePickerDialog.OnDateSetListener onDateSetListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_editdate,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        mDatabase = FirebaseDatabase.getInstance().getReference("Student");
        button=(Button) view.findViewById(R.id.bns);
        button.setText("Update");
        textView=(TextView)view.findViewById(R.id.sdtext);
        textView1=view.findViewById(R.id.textView);
        cancel=view.findViewById(R.id.cancel);
        Bundle bundle=getArguments();
        date=bundle.getString("date");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(container.getContext(),android.R.style.Theme_Holo_Dialog_MinWidth
                        ,onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                textView.setText(day +" / "+ month +" / "+ year);
                years=year;
                string=textView.getText().toString();

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                if(textView.getText().toString().equals("Select Date")){
                    textView.setError("Please Select Date!");
                    textView.requestFocus();
                }else if(year-7<years){
                    textView.setError("Please Select current Date!");
                    textView.requestFocus();
                }else {
                    if (date.equals(string)){
                        textView1.setText("Your Birthday In The Same Day!");
                        textView1.setTextColor(Color.RED);
                        textView1.setVisibility(View.VISIBLE);
                    }else{
                    Bundle bundle = getArguments();
                    phone = bundle.getString("phone");
                    Student student = new Student();
                    student.setDate(string);
                    mDatabase.child(phone).child("date").setValue(student.getDate());
                    Intent intent=new Intent(getActivity(),TabbedActivity.class);
                    startActivityForResult(intent,1000);
                }}
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                phone = bundle.getString("phone");
                Student student = new Student();
                student.setDate(date);
                mDatabase.child(phone).child("date").setValue(student.getDate());
                Intent intent=new Intent(getActivity(),TabbedActivity.class);
                startActivityForResult(intent,1000);
            }
        });


        return view;
        }
}
