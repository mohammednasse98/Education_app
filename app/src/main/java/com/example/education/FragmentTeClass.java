package com.example.education;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.client.Firebase;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentTeClass extends Fragment {
    String material[]={"Math","English","Chemistry","Physics","Computer","Biology","ِArabic",
            "Geology","Islamic Sciences","Geography"};
    int[] images={R.drawable.math,R.drawable.english,R.drawable.chemistry,
            R.drawable.physics,R.drawable.computer,R.drawable.biology,
            R.drawable.arabic,R.drawable.geology,R.drawable.islamic,R.drawable.georaphy};
    ListView listView;
    String string="";
    String name,phone,email,password,gender,date,city,image,choose;
    Firebase mRootRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_te,container,false);
        listView=view.findViewById(R.id.listView);
        Bundle bundle=getArguments();
        name=bundle.getString("name");
        phone=bundle.getString("phone");
        email=bundle.getString("email");
        password=bundle.getString("password");
        gender=bundle.getString("gender");
        date=bundle.getString("date");
        city=bundle.getString("city");
        image=bundle.getString("image");
        choose=bundle.getString("choose");
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        MyAdapter myAdapter=new MyAdapter(getActivity(),material,images);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                string=material[position];
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Are You Sure To Continue? \n \n");
                builder.setMessage("Selected Material: "+string);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(getActivity());
                        pd.setTitle("Signing Up...");
                        pd.show();
                        Firebase firebase =mRootRef.child("Teacher").child(string).child(phone);
                        Teacher teacher=new Teacher();
                        teacher.setName(name);
                        teacher.setPhone(phone);
                        teacher.setDate(date);
                        teacher.setChoose(choose);
                        teacher.setPassword(password);
                        teacher.setGender(gender);
                        teacher.setTeacherClass(string);
                        teacher.setEmail(email);
                        teacher.setCity(city);
                        teacher.setImage(image);
                        teacher.setEvaluation("0");
                        firebase.setValue(teacher);
                        Intent intent=new Intent(getActivity(),LogIn.class);
                        intent.putExtra("Password",bundle.getString("Password"));
                        intent.putExtra("Phone",phone);
                        startActivityForResult(intent,1);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        return view;
    }


    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String material[];
        int images[];
        MyAdapter(Context context,String material[],int images[]){
            super(context,R.layout.design_list,R.id.textView1,material);
            this.context=context;
            this.material=material;
            this.images=images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.design_list,parent,false);
            CircleImageView imageView=view.findViewById(R.id.imageView);
            TextView nMaterial=view.findViewById(R.id.textView1);
            imageView.setImageResource(images[position]);
            nMaterial.setText(material[position]);
            return view;
        }

    }
}