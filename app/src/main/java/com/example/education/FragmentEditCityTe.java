package com.example.education;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentEditCityTe extends Fragment {
    ListView listView;
    TextView textView;
    EditText editText;
    Button button,cancel;
    String str="";
    String city1="";
    String material="";
    private DatabaseReference mDatabase;
    private Firebase mRootRef;
    String phone="";
    String city []={"Amman","Zarqa","Irbid","Aqaba","Balqa","Madaba","al-Mafraq","Ma'an","at-Tafila","al-Karak","Jerash",
            "Ajloun"};
    String string="";
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_editcity,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        mDatabase = FirebaseDatabase.getInstance().getReference("Teacher");
        editText=view.findViewById(R.id.editText);
        listView=view.findViewById(R.id.listchoos);
        button=view.findViewById(R.id.choose);
        cancel=view.findViewById(R.id.cancel);
        textView=view.findViewById(R.id.textView);
        button.setText("Update");
        Bundle bundle=getArguments();
        city1=bundle.getString("city");
        material=bundle.getString("teacherClass");
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(container.getContext(),android.R.layout.simple_list_item_single_choice,city);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str=city[position];
                string=city[position];
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.equals("")){

                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Please Choose your City!");
                }else{
                    if(city1.equals(string)){
                        textView.setText("The City is already Chosen!");
                        textView.setTextColor(Color.RED);
                        textView.setVisibility(View.VISIBLE);
                    }else{
                        Bundle bundle=getArguments();
                        phone=bundle.getString("phone");
                        Student student=new Student();
                        student.setCity(string);
                        mDatabase.child(material).child(phone).child("city").setValue(student.getCity());
                        Intent intent=new Intent(getActivity(),TabbedActivity.class);
                        startActivityForResult(intent,1000);}
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getArguments();
                phone=bundle.getString("phone");
                Student student=new Student();
                student.setCity(city1);
                mDatabase.child(material).child(phone).child("city").setValue(student.getCity());
                Intent intent=new Intent(getActivity(),TabbedActivity.class);
                startActivityForResult(intent,1000);
            }
        });
        return view;
    }
}
