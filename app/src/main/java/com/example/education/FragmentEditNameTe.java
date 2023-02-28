package com.example.education;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentEditNameTe extends Fragment {
    View view;
    EditText editFirst,editLast;
    String phone;
    Button change;
    Button cancel;
    private DatabaseReference mDatabase;
    private Firebase mRootRef;
    TextView textView;
    String name="";
    String material="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_editname,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        editFirst=view.findViewById(R.id.editFirst);
        editLast=view.findViewById(R.id.editLast);
        change=view.findViewById(R.id.change);
        mDatabase = FirebaseDatabase.getInstance().getReference("Teacher");
        cancel=view.findViewById(R.id.cancel);
        textView=view.findViewById(R.id.textView);
        Bundle bundle=getArguments();
        name=bundle.getString("name");
        material=bundle.getString("teacherClass");
        System.out.println(material);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=editFirst.getText().toString()+" "+editLast.getText().toString();

                if (editFirst.getText().toString().equals("")||editFirst.getText().toString().isEmpty()) {
                    editFirst.setError("First Name Should not be blank");
                    editFirst.requestFocus();

                }else if(editLast.getText().toString().equals("")||editLast.getText().toString().isEmpty()) {

                    editLast.setError("Last Name Should not be blank");
                    editLast.requestFocus();

                }else if(name1.equals(name)){
                    textView.setText("The Name is already used!");
                    textView.setTextColor(Color.RED);
                    textView.setVisibility(View.VISIBLE);
                }
                else{
                    textView.setVisibility(View.VISIBLE);
                    String fullName=editFirst.getText().toString()+" "+editLast.getText().toString();

                    Student student=new Student();
                    student.setName(fullName);
                    Bundle bundle=getArguments();
                    phone=bundle.getString("phone");
                    mDatabase.child(material).child(phone).child("name").setValue(student.getName());
                    Intent intent=new Intent(getActivity(),TabbedActivity.class);
                    startActivity(intent);
                }}
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LogIn.class);
                intent.putExtra("Num","1");

            }
        });

        return view;
    }
}
