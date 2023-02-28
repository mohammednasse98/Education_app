package com.example.education;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Map;

public class LogIn extends AppCompatActivity {
    CheckBox remeber;
    EditText phoneText;
    EditText passwordText;
    Firebase mRootRef;
    String password,phone;
    String checkBox;
    private FirebaseAuth auth;
    TextView textView,signUp;
    Button button;
    String teacherClass="";

    int i = 0;
    String material[]={"Math","English","Chemistry","Physics","Computer","Biology","ِArabic",
            "Geology","Islamic Sciences","Geography"};

    public void logIn(View view) {

        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Student").hasChild(phoneText.getText().toString())){
                    Map<String,String> map=dataSnapshot.child("Student").child(phoneText.getText().toString()).getValue(Map.class);
                    new Intent(LogIn.this,TabbedActivity.class);
                    System.out.println(map);
                    String password=map.get("password");
                    String name = map.get("name");
                    String phone1 = map.get("phone");
                    String gender = map.get("gender");
                    String city =map.get("city");
                    String date =map.get("date");
                    String image=map.get("image");
                    String email=map.get("email");
                    System.out.println(password);

                    if (passwordText.getText().toString().equals(password)) {
                        Intent intent = new Intent(LogIn.this, TabbedActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("phone",phone1);
                        intent.putExtra("gender",gender);
                        intent.putExtra("city",city);
                        intent.putExtra("password",password);
                        intent.putExtra("date",date);
                        intent.putExtra("image",image);
                        startActivity(intent);


                    } else {
                        passwordText.setError("Please enter your password correct!");
                        passwordText.requestFocus();
                    }

                }else {
                    for (i=0; i < material.length; i++) {
                        if (dataSnapshot.child("Teacher").child(material[i]).hasChild(phoneText.getText().toString())) {
                            Map<String, String> map = dataSnapshot.child("Teacher").child(material[i]).child(phoneText.getText().toString()).getValue(Map.class);

                            System.out.println(map);
                            String password = map.get("password");
                            String name = map.get("name");
                            String phone1 = map.get("phone");
                            String gender = map.get("gender");
                            String city = map.get("city");
                            String date = map.get("date");
                            String teacherClass = map.get("teacherClass");
                            String image = map.get("image");
                            String email=map.get("email");


                            System.out.println(password);

                            if (passwordText.getText().toString().equals(password)) {
                                Intent intent1 = new Intent(LogIn.this, TabbedTe.class);
                                intent1.putExtra("name", name);
                                intent1.putExtra("phone", phone1);
                                intent1.putExtra("gender", gender);
                                intent1.putExtra("city", city);
                                intent1.putExtra("password", password);
                                intent1.putExtra("date", date);
                                intent1.putExtra("teacherClass", teacherClass);
                                intent1.putExtra("image", image);
                                startActivity(intent1);
                                break;


                            } else {
                                passwordText.setError("Please enter your password correct!");
                                passwordText.requestFocus();
                            }
                        }
                    }
                    if (i==material.length){
                        phoneText.setError("Please enter your phone correct!");
                        phoneText.requestFocus();
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        //  intent=new Intent(LogIn.this,TabbedActivity.class);
        button=findViewById(R.id.login);
        textView=findViewById(R.id.textShow);
        phoneText=(EditText) findViewById(R.id.phone);
        passwordText=(EditText)findViewById(R.id.password);
        remeber=(CheckBox)findViewById(R.id.checkBox);
        signUp=(TextView)findViewById(R.id.signUp);
        auth = FirebaseAuth.getInstance();
        Intent intent=getIntent();
        password= intent.getStringExtra("Password");
        phone=intent.getStringExtra("Phone");
        teacherClass=intent.getStringExtra("teacherCLass");
        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        checkBox =preferences.getString("remember","");
        textView.setVisibility(View.INVISIBLE);
        textView.setText("SHOW");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        if(checkBox.equals("true")){

        }else if(checkBox.equals("false")){

        }

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passwordText.getText().toString().length()>0){
                    textView.setVisibility(View.VISIBLE);
                }else{
                    textView.setVisibility(View.INVISIBLE);
                    passwordText.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordText.setSelection(passwordText.length());}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().equals("SHOW")){
                    textView.setText("HIDE");
                    passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordText.setSelection(passwordText.length());
                }else if (textView.getText().toString().equals("HIDE")){
                    textView.setText("SHOW");
                    passwordText.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordText.setSelection(passwordText.length());

                }
            }
        });
        remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    phoneText.setText(phone);
                    passwordText.setText(password);
                }else if(!buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("cheakbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LogIn.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}