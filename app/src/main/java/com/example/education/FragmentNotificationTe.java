package com.example.education;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentNotificationTe extends Fragment {
    View view;
    RecyclerView recyclerView;
    DatabaseReference reference;
    Firebase mRootRef;
    String phone="",name="",city="",teacherClass="",image="";
    String hour;
    String material;
    String phoneBundle;
    String id;
    String evaluation="";
    ArrayList<String> arrayList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_notificationte,container,false);
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        Bundle bundle=getArguments();
        phone=bundle.getString("phone");
        name=bundle.getString("name");
        city=bundle.getString("city");
        teacherClass=bundle.getString("teacherClass");
        image=bundle.getString("image");
        reference=FirebaseDatabase.getInstance().getReference();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle=getArguments();

        FirebaseRecyclerOptions<pushValue> option = new FirebaseRecyclerOptions.Builder<pushValue>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Notification").child(bundle.getString("phone")), pushValue.class)
                .build();

        FirebaseRecyclerAdapter<pushValue, FragmentNotificationTe.myviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<pushValue,
                FragmentNotificationTe.myviewholder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull FragmentNotificationTe.myviewholder holder, int position, @NonNull pushValue pushValue) {
                evaluation=pushValue.getEvaluation();
                hour=pushValue.getHour();
                material=pushValue.getMaterial();
                phoneBundle=pushValue.getPhone();
                if (pushValue.getStatus().equals("")){
                    holder.reject.setVisibility(View.VISIBLE);
                    holder.accept.setVisibility(View.VISIBLE);}
                if (pushValue.getStatus().equals("Rejected")){
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.accept.setVisibility(View.INVISIBLE);
                }if (pushValue.getStatus().equals("Accepted")){
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.accept.setVisibility(View.INVISIBLE);
                }
                if(pushValue.getStatus()==null) {
                    holder.textViewHourAndStutus.setText("Hours Needed: " + pushValue.getHour() + "\n");
                    holder.nameTextView.setText("Name: "+pushValue.getName());
                    holder.textViewPhone.setText("Phone: "+pushValue.getPhone());
                    holder.textViewCity.setText("City: "+pushValue.getCity());
                }else{
                    holder.textViewHourAndStutus.setText("Hours Needed: " + pushValue.getHour()+ "\n");
                    holder.status.setText("Status: "+pushValue.getStatusStudent());
                    holder.nameTextView.setText("Name: "+pushValue.getName());
                    holder.textViewPhone.setText("Phone: "+pushValue.getPhone());
                    holder.textViewCity.setText("City: "+pushValue.getCity());
                }
                Picasso.get().load(pushValue.getImage()).into(holder.circleImageView);
                holder.accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        id=pushValue.getId();
                        dialog();

                    }
                });
                holder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final ProgressDialog pd = new ProgressDialog(getActivity());
                        pd.setTitle("Sending...");
                        pd.show();
                        Firebase firebase = mRootRef.child("Notification").child(pushValue.getPhone()).push();
                        Firebase firebase1=mRootRef.child("Notification").child(bundle.getString("phone")).child(pushValue.getId()).child("status");
                        firebase1.setValue("Rejected");
                        NotificationTeacherClass notificationTeacherClass = new NotificationTeacherClass();
                        notificationTeacherClass.setName(name);
                        notificationTeacherClass.setPhone(phone);
                        notificationTeacherClass.setCity(city);
                        notificationTeacherClass.setImage(image);
                        notificationTeacherClass.setStatus("Rejected");
                        notificationTeacherClass.setHour(hour);
                        notificationTeacherClass.setId(pushValue.getId());
                        notificationTeacherClass.setIdStudent("");
                        notificationTeacherClass.setStatusStudent("Rejected");
                        notificationTeacherClass.setMaterial(material);
                        notificationTeacherClass.setRating("");
                        mRootRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child("Notification").hasChild(bundle.getString("phone"))){
                                    Map<String,String> map=dataSnapshot.child("Notification").child(bundle.getString("phone")).getValue(Map.class);
                                    for( String key: map.keySet()) {
                                        arrayList.add(key);
                                        Firebase firebase1= mRootRef.child("Notification").child(bundle.getString("phone")).child(key).child("idStudent");
                                        firebase1.setValue(key);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        firebase.setValue(notificationTeacherClass);
                    }
                });
            }
            @NonNull
            @Override
            public FragmentNotificationTe.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_teacher,parent,false);
                return new FragmentNotificationTe.myviewholder(view);
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    class myviewholder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView textViewPhone;
        TextView textViewHourAndStutus;
        TextView textViewCity;
        RelativeLayout relative;
        CircleImageView circleImageView;
        CircleImageView accept;
        CircleImageView reject;
        TextView status;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewHourAndStutus = itemView.findViewById(R.id.textViewHourAndStutus);
            circleImageView = itemView.findViewById(R.id.image);
            textViewCity=itemView.findViewById(R.id.textViewCity);
            relative=itemView.findViewById(R.id.relative);
            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
            status=itemView.findViewById(R.id.status);

        }
    }
    private void dialog() {
        Bundle bundle=new Bundle();
        bundle.putString("phone",phone);
        bundle.putString("name",name);
        bundle.putString("city",city);
        bundle.putString("image",image);
        bundle.putString("hour",hour);
        bundle.putString("material",material);
        bundle.putString("phoneStudent",phoneBundle);
        bundle.putString("id",id);
        bundle.putString("evaluation",evaluation);
        NotificationDialog notificationDialog=new NotificationDialog();
        notificationDialog.setArguments(bundle);
        notificationDialog.show(getActivity().getSupportFragmentManager(),"dialog ");
    }
}
