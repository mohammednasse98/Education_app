package com.example.education;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class NotificationDialog extends AppCompatDialogFragment {
    View view;
    EditText perHour,hourText,minutesText;
    TextView goodDate;
    int years=0;
    String stringDate="";
    String phone="",name="",city="",image="";
    String hour;
    String material;
    Firebase mRootRef;
    String evaluation="";

    String id;
    ArrayList<String> arrayList=new ArrayList<>();
    DatePickerDialog.OnDateSetListener onDateSetListener;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.notification_dialog,null);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        perHour=view.findViewById(R.id.perHour);
        goodDate=view.findViewById(R.id.goodDate);
        hourText=view.findViewById(R.id.hour);
        minutesText=view.findViewById(R.id.minutes);

        Bundle bundle=getArguments();
        material=bundle.getString("material");
        phone=bundle.getString("phone");
        name=bundle.getString("name");
        city=bundle.getString("city");
        image=bundle.getString("image");
        hour=bundle.getString("hour");
        id=bundle.getString("id");
        evaluation=bundle.getString("evaluation");

        goodDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_DARK
                        ,onDateSetListener,year,month+1,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

                datePickerDialog.show();

            }
        });
        onDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                goodDate.setText(day +"/"+ month +"/"+ year);
                stringDate=goodDate.getText().toString();
            }
        };
        if(perHour.getText().toString().equals("")){
            perHour.setError("Please Enter Your Per Hour");
        }

        builder.setView(view)
                .setTitle("Please Fill The Form: ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(stringDate.equals("The right time for you: ")){
                            goodDate.setError("Please Enter The ");
                        }else{
                            final ProgressDialog pd = new ProgressDialog(getActivity());
                            pd.setTitle("Sending...");
                            pd.show();
                            Bundle bundle=getArguments();

                            Firebase firebase = mRootRef.child("Notification").child(bundle.getString("phoneStudent")).push();
                            Firebase firebase1=mRootRef.child("Notification").child(phone).child(id).child("status");
                            firebase1.setValue("Accepted");
                            NotificationTeacherClass notificationTeacherClass = new NotificationTeacherClass();
                            notificationTeacherClass.setName(name);
                            notificationTeacherClass.setPhone(phone);
                            notificationTeacherClass.setCity(city);
                            notificationTeacherClass.setImage(image);
                            notificationTeacherClass.setPerHour(perHour.getText().toString()+"$");
                            notificationTeacherClass.setStatus("Accepted");
                            notificationTeacherClass.setHour(hour);
                            notificationTeacherClass.setEvaluation(evaluation);
                            notificationTeacherClass.setId(id);
                            notificationTeacherClass.setRating("");
                            notificationTeacherClass.setIdStudent("");
                            notificationTeacherClass.setStatusStudent("");
                            notificationTeacherClass.setMaterial(material);
                            int amPm= Integer.parseInt(hourText.getText().toString());
                            if(amPm>=12){
                                notificationTeacherClass.setTime(hourText.getText().toString()+":"+minutesText.getText().toString()+" PM");
                            }else{
                                notificationTeacherClass.setTime(hourText.getText().toString()+":"+minutesText.getText().toString()+" AM");
                            }
                            notificationTeacherClass.setGoodDate(stringDate);
                            mRootRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child("Notification").hasChild(bundle.getString("phoneStudent"))){
                                        Map<String,String> map=dataSnapshot.child("Notification").child(bundle.getString("phoneStudent")).getValue(Map.class);
                                        for( String key: map.keySet()) {
                                            arrayList.add(key);
                                            Firebase firebase1= mRootRef.child("Notification").child(bundle.getString("phoneStudent")).child(key).child("idStudent");
                                            firebase1.setValue(key);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                            firebase.setValue(notificationTeacherClass);
                            Toast.makeText(getActivity(), "Sent Succesfully...", Toast.LENGTH_LONG).show();
                        }
                    }
                }) .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
