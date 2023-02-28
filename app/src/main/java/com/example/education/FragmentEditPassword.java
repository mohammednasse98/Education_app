package com.example.education;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class FragmentEditPassword extends Fragment {
    View view;
    EditText currentPassword,newPassword,reTypePassword;
    Button update,cancel;
    String password="";
    String string="";
    private DatabaseReference mDatabase;
    private Firebase mRootRef;
    String phone="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_editpassword,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        currentPassword=view.findViewById(R.id.currentPassword);
        newPassword=view.findViewById(R.id.newPassword);
        reTypePassword=view.findViewById(R.id.reTypePassword);
        update=view.findViewById(R.id.update);
        mDatabase = FirebaseDatabase.getInstance().getReference("Student");
        cancel=view.findViewById(R.id.cancel);
        Bundle bundle = getArguments();
        password = bundle.getString("password");

        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (newPassword.length() <= 7) {
                    newPassword.setError("Please enter a strong password!");
                    newPassword.requestFocus();
                } else{

                    string=newPassword.getText().toString();
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        reTypePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (reTypePassword.getText().toString().equals(reTypePassword.getText().toString()) ) {
                    update.setVisibility(View.VISIBLE);
                } else if (reTypePassword.getText().toString().length() == 0 || reTypePassword.getText().toString().equals(null) || reTypePassword.getText().toString() != reTypePassword.getText().toString()|| reTypePassword.length()<7) {
                    reTypePassword.setError("Please enter same password!");
                    reTypePassword.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.equals(currentPassword.getText().toString()) || currentPassword.getText().toString().isEmpty()) {
                    currentPassword.setError("Your password was incorrect.");
                    currentPassword.requestFocus();
                } else if (newPassword.getText().toString().equals(currentPassword.getText().toString())&&currentPassword.getText().toString().equals(password)){
                    newPassword.setError("it's the same password!");
                    newPassword.requestFocus();
            }else{
                if(reTypePassword.getText().toString().equals("")){
                    reTypePassword.setError("Please enter same password!");
                    reTypePassword.requestFocus();

                }else{
                    String password1=newPassword.getText().toString();
                    Student student=new Student();
                    student.setPassword(password1);
                    Bundle bundle1=getArguments();
                    phone=bundle1.getString("phone");
                    mDatabase.child(phone).child("password").setValue(student.getPassword());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Your Password has been updated!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getActivity(),LogIn.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student();
                student.setPassword(password);
                Bundle bundle1=getArguments();
                phone=bundle1.getString("phone");
                mDatabase.child(phone).child("password").setValue(student.getPassword());
                Intent intent=new Intent(getActivity(),TabbedActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
