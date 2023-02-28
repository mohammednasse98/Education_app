package com.example.education;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentEditGenderTe extends Fragment {
    View view;
    ListView listView;
    Button button,cancel;
    String str[]={"Male","Female"};
    TextView textView,textView1;
    String string="";
    String phone="";
    String gender="";
    String material="";
    private DatabaseReference mDatabase;
    private Firebase mRootRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_editgender,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        listView=(ListView)view.findViewById(R.id.gender);
        button=(Button)view.findViewById(R.id.bge);
        mDatabase = FirebaseDatabase.getInstance().getReference("Teacher");
        button.setText("Update");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(container.getContext(),android.R.layout.simple_list_item_single_choice,str);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        textView=(TextView)view.findViewById(R.id.tl);
        textView1=view.findViewById(R.id.textView);
        cancel=view.findViewById(R.id.cancel);
        Bundle bundle=getArguments();
        gender=bundle.getString("gender");
        material=bundle.getString("teacherClass");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setVisibility(View.INVISIBLE);
                string= str[i];
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.isItemChecked(0)||listView.isItemChecked(1)){
                    if(gender.equals(string)){
                        textView1.setText("The Gender is already Chosen!");
                        textView1.setTextColor(Color.RED);
                        textView1.setVisibility(View.VISIBLE);
                    }else {
                        Bundle bundle=getArguments();
                        phone=bundle.getString("phone");
                        Student student=new Student();
                        student.setGender(string);
                        mDatabase.child(material).child(phone).child("gender").setValue(student.getGender());
                        Intent intent=new Intent(getActivity(),TabbedActivity.class);
                        startActivityForResult(intent,1000);
                    }
                }else {
                    textView.setText("Please tell us your gender!");
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getArguments();
                phone=bundle.getString("phone");
                Student student=new Student();
                student.setGender(gender);
                mDatabase.child(material).child(phone).child("gender").setValue(student.getGender());
                Intent intent=new Intent(getActivity(),TabbedActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
