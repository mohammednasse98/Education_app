package com.example.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recycle extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    DatabaseReference reference1;
    Firebase mRootRef;
    String material="";
    String phone="";
    String name="";
    String city="";
    String date="";
    String image="";
    String hour="";
    String evaluation="";
    ArrayList <String>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        Intent intent=getIntent();
        material=intent.getStringExtra("Material");
        phone=intent.getStringExtra("phone");
        name=intent.getStringExtra("name");
        city=intent.getStringExtra("city");
        date=intent.getStringExtra("date");
        image=intent.getStringExtra("image");
        hour=intent.getStringExtra("hour");
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference= FirebaseDatabase.getInstance().getReference();
        reference1= FirebaseDatabase.getInstance().getReference("Teacher");

    }
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Model> option = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Teacher").child(material), Model.class)
                .build();


        FirebaseRecyclerAdapter<Model,myviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Model,myviewholder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Model model) {
                evaluation=model.getEvaluation();
                holder.nameTeacher.setText(model.getName());
                holder.classTeacher.setText(model.getTeacherClass());
                holder.city.setText(model.getCity());
                Picasso.get().load(model.getImage()).into(holder.circleImageView);
                if (model.getEvaluation().equals("0")){
                    holder.ratingBar.setVisibility(View.INVISIBLE);
                    holder.newUser.setVisibility(View.VISIBLE);
                }else{
                    holder.ratingBar.setRating(Float.parseFloat(model.getEvaluation()));

                    holder.ratingBar.setVisibility(View.VISIBLE);
                    holder.newUser.setVisibility(View.INVISIBLE);
                }
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog pd = new ProgressDialog(Recycle.this);
                        pd.setTitle("Sending...");
                        pd.show();
                        Firebase firebase = mRootRef.child("Notification").child(model.getPhone()).push();
                        pushValue pushValue = new pushValue();
                        pushValue.setName(name);
                        pushValue.setPhone(phone);
                        pushValue.setCity(city);
                        pushValue.setDate(date);
                        pushValue.setImage(image);
                        pushValue.setMaterial(material);
                        pushValue.setHour(hour);
                        pushValue.setEvaluation(evaluation);
                        pushValue.setId("");
                        pushValue.setStatus("");
                        pushValue.setStatusStudent("");
                        firebase.setValue(pushValue);
                        mRootRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child("Notification").hasChild(model.getPhone())){
                                    Map <String,String> map=dataSnapshot.child("Notification").child(model.getPhone()).getValue(Map.class);
                                    for( String key: map.keySet()) {
                                        arrayList.add(key);
                                        Firebase firebase1= mRootRef.child("Notification").child(model.getPhone()).child(key).child("id");
                                        firebase1.setValue(key);
                                        System.out.println(key);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                    }
                });
            }
            @NonNull
            @Override
            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_view,parent,false);
                return new myviewholder(view);
            }

        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
    static class myviewholder extends RecyclerView.ViewHolder {
        TextView nameTeacher;
        TextView classTeacher;
        TextView city;

        Button button;
        CircleImageView circleImageView;
        RatingBar ratingBar;
        RelativeLayout relativeLayout;
        TextView newUser;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            nameTeacher = itemView.findViewById(R.id.name);
            classTeacher = itemView.findViewById(R.id.classTeacher);
            city = itemView.findViewById(R.id.city);
            circleImageView = itemView.findViewById(R.id.image);
            button = itemView.findViewById(R.id.button);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            relativeLayout=itemView.findViewById(R.id.re);
            newUser=itemView.findViewById(R.id.newUser);
        }
    }
}