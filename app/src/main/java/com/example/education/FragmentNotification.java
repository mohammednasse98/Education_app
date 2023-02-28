package com.example.education;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentNotification extends Fragment {
    View view;
    RecyclerView recyclerView;
    Firebase mRootRef;
    String phone="",name="",city="",teacherClass="",image="";
    DatabaseReference reference;
    private static  final int REQUEST_CALL=1;
    String evaluation="";

    String nameString,phoneString,idString,statusStudent,teacherClass1,cityString,imageString,idStudent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_notification,container,false);
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        Bundle bundle=getArguments();
        phone=bundle.getString("phone");
        name=bundle.getString("name");
        city=bundle.getString("city");
        image=bundle.getString("image");
        reference= FirebaseDatabase.getInstance().getReference();
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle=getArguments();
        FirebaseRecyclerOptions<NotificationTeacherClass> option = new FirebaseRecyclerOptions.Builder<NotificationTeacherClass>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Notification").child(bundle.getString("phone")),
                        NotificationTeacherClass.class)
                .build();
        FirebaseRecyclerAdapter<NotificationTeacherClass, FragmentNotification.myviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NotificationTeacherClass,
                FragmentNotification.myviewholder>(option) {

            @Override
            protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull NotificationTeacherClass notificationTeacherClass) {
                evaluation=notificationTeacherClass.getEvaluation();
                    nameString=notificationTeacherClass.getName();
                    phoneString=notificationTeacherClass.getPhone();
                    idString=notificationTeacherClass.getId();
                teacherClass1=notificationTeacherClass.getMaterial();
                idStudent=notificationTeacherClass.getIdStudent();
            if (notificationTeacherClass.getRating().equals(""))
                {
                    holder.rateTeacher.setText("Rate: "+"The Teacher");
                    holder.rateTeacher.setVisibility(View.VISIBLE);
                }if (notificationTeacherClass.getRating().equals("Evaluated")){
                    holder.rateTeacher.setText("Evaluated");
                    holder.rateTeacher.setVisibility(View.INVISIBLE);
                    holder.rateTeacher.setText("");

                }else{
                    holder.rateTeacher.setVisibility(View.VISIBLE);
                    holder.swap.setVisibility(View.INVISIBLE);
                }
                if (notificationTeacherClass.getStatusStudent().equals("")){
                    holder.reject.setVisibility(View.VISIBLE);
                    holder.accept.setVisibility(View.VISIBLE);}
                if (notificationTeacherClass.getStatusStudent().equals("Rejected")){
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.accept.setVisibility(View.INVISIBLE);
                    holder.rateTeacher.setVisibility(View.INVISIBLE);
                }if (notificationTeacherClass.getStatusStudent().equals("Accepted")){
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.accept.setVisibility(View.INVISIBLE);
                    holder.rateTeacher.setVisibility(View.VISIBLE);
                }
                holder.nameTextView.setText("Name: "+notificationTeacherClass.getName());
                holder.textViewPhone.setText("Phone: "+notificationTeacherClass.getPhone());
                holder.textViewCity.setText("City: "+notificationTeacherClass.getCity());
                if(notificationTeacherClass.getStatus().equals("Accepted")) {
                    holder.textViewHourAndStutus.setText("Per Hour: " + notificationTeacherClass.getPerHour());
                    holder.status.setText("Status: "+notificationTeacherClass.getStatus());
                    holder.dateTime.setText("Date And Time: \n"+notificationTeacherClass.getGoodDate()+" "+notificationTeacherClass.getTime());

                }else{
                    holder.status.setText("Status: " + notificationTeacherClass.getStatus() );
                    holder.rateTeacher.setVisibility(View.INVISIBLE);
                }
                holder.rateTeacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dialog();
                    }
                });
                Picasso.get().load(notificationTeacherClass.getImage()).into(holder.circleImageView);
                holder.accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("Call: "+notificationTeacherClass.getName())
                                .setMessage(notificationTeacherClass.getPhone())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Intent intent=new Intent(Intent.ACTION_DIAL);
                                        intent.setData(Uri.parse("tel: "+notificationTeacherClass.getPhone()));
                                        startActivityForResult(intent,1);
                                        Firebase firebase=mRootRef.child("Notification").child(notificationTeacherClass.getPhone())
                                                .child(notificationTeacherClass.getId()).child("statusStudent");
                                        firebase.setValue("Accepted");
                                        Firebase firebase1=mRootRef.child("Notification").child(bundle.getString("phone"))
                                                .child(notificationTeacherClass.getIdStudent()).child("statusStudent");
                                        firebase1.setValue("Accepted");


                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder.show();



                    }
                });
                holder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.textViewHourAndStutus.setText("Status: Rejected"+ "\n");
                        final ProgressDialog pd = new ProgressDialog(getActivity());
                        pd.setTitle("Sending...");
                        pd.show();
                        Bundle bundle=getArguments();
                        Firebase firebase=mRootRef.child("Notification").child(notificationTeacherClass.getPhone())
                                .child(notificationTeacherClass.getId()).child("statusStudent");
                        firebase.setValue("Rejected");
                        Firebase firebase1=mRootRef.child("Notification").child(bundle.getString("phone"))
                                .child(notificationTeacherClass.getIdStudent()).child("statusStudent");
                        firebase1.setValue("Rejected");

                    }
                });
            }

            @NonNull
            @Override
            public FragmentNotification.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_student,parent,false);
                return new FragmentNotification.myviewholder(view);
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
        TextView dateTime;
        TextView status;
        RelativeLayout relative;
        CircleImageView circleImageView;
        CircleImageView accept;
        CircleImageView reject;
        TextView rateTeacher;
        TextView swap;
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
            dateTime=itemView.findViewById(R.id.dateTime);
            rateTeacher=itemView.findViewById(R.id.rateTeacher);
            swap=itemView.findViewById(R.id.swap);
        }
    }



    private void dialog() {
        Bundle bundle=new Bundle();
        bundle.putString("TeacherName",nameString);
        bundle.putString("PhoneTeacher",phoneString);
        bundle.putString("IdTeacher",idString);
        bundle.putString("idStudent",idStudent);
        bundle.putString("phoneStudent",phone);
        bundle.putString("Material",teacherClass1);
        bundle.putString("evaluation",evaluation);
        DialogRate dialogRate=new DialogRate();
        dialogRate.setArguments(bundle);
        dialogRate.show(getActivity().getSupportFragmentManager(),"dialog frag");
    }

}


