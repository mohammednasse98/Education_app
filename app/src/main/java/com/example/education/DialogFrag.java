package com.example.education;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFrag extends AppCompatDialogFragment {
    View view;
    Button button,button1;
    TextView textView;
    int x=0;
    String material="";
    String phone="";
    String name="";
    String city="";
    String date="";
    String image="";
    String hour="";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            LayoutInflater inflater=getActivity().getLayoutInflater();
            view=inflater.inflate(R.layout.dialog_design,null);
                Bundle bundle=getArguments();
        material=bundle.getString("Material");
        phone=bundle.getString("phone");
        name=bundle.getString("name");
        city=bundle.getString("city");
        date=bundle.getString("date");
        image=bundle.getString("image");
              button=view.findViewById(R.id.decrease);
            button1=view.findViewById(R.id.increase);
               textView=view.findViewById(R.id.textView1);
             textView.setText("0");
            builder.setView(view)
                    .setTitle(material+":")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hour=textView.getText().toString();
                            Intent intent=new Intent(getActivity(),Recycle.class);
                            intent.putExtra("Material",material);
                            intent.putExtra("phone",phone);
                            intent.putExtra("name",name);
                            intent.putExtra("city",city);
                            intent.putExtra("date",date);
                            intent.putExtra("image",image);
                            intent.putExtra("hour",hour);
                            startActivity(intent);
                        }
                    });
           button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x>0){
                    x-- ;
                    textView.setText(String.valueOf(x));

                }

            }
        });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        x++;
                        textView.setText(String.valueOf(x));
                        if(x>15){
                          x=0;
                            textView.setText(String.valueOf(x));
                        }
                    }
                });

        return builder.create();
        }
}
