package com.example.education;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class DialogRate extends AppCompatDialogFragment {
    View view;
    Firebase mRootRef;
    RatingBar ratingBar;
        int x=0;
    String name,phoneTeacher,phoneStudent,idTeacher,idStudnet,evaluation;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.rate_dialog,null);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        ratingBar=view.findViewById(R.id.rateTeacher);
        Bundle bundle=getArguments();
       name= bundle.getString("TeacherName");
       phoneTeacher= bundle.getString("PhoneTeacher");
        idTeacher=bundle.getString("IdTeacher");
        idStudnet=bundle.getString("idStudent");
        phoneStudent=bundle.getString("phoneStudent");
        evaluation=bundle.getString("evaluation");
        builder.setView(view)
                .setTitle("Please Rate: "+name)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Float preivousEvaluation=Float.parseFloat(evaluation);
                        Firebase firebase=mRootRef.child("Notification").child(phoneStudent)
                                .child(idStudnet).child("rating");
                        firebase.setValue("Evaluated");

                        Firebase firebase1=mRootRef.child("Teacher").child(bundle.getString("Material"))
                                .child(phoneTeacher).child("evaluation");

                        firebase1.setValue((preivousEvaluation+ratingBar.getRating())/2+"");
                        dialog.dismiss();

                    }

                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });



        return builder.create();
    }
}

