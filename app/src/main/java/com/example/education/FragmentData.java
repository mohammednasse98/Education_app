package com.example.education;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
public class FragmentData extends Fragment {
    View view;
    private CircleImageView imageView;
    Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private StorageReference riversRef;
    String imageUrl,fullName,stringPassword="",stringDate="",stringCity="",stringGender="",choose="";
    EditText firstName,lastNmae,phone,password,email,rePassword;
    Firebase mRootRef;
    int j=0;
    RadioGroup radioGroup;

    RadioButton radioMale,radioFemale;
    TextView textDate,textErrorGender,textErrorCity,textBirth;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int years=0;
    Spinner city;
    Button button;
    Drawable oldDrawable;
    private FirebaseAuth auth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.design_data,container,false);
        Firebase.setAndroidContext(getActivity());
        mRootRef = new Firebase("https://education-9a337-default-rtdb.firebaseio.com/");
        auth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        imageView=view.findViewById(R.id.image);
        firstName =view.findViewById(R.id.firstName);
        lastNmae =view.findViewById(R.id.lastName);
        fullName= firstName.getText().toString()+" "+lastNmae.getText().toString();
        phone=view.findViewById(R.id.phone);
        password=(EditText)view.findViewById(R.id.password);
        email = (EditText) view.findViewById(R.id.email);
        rePassword=(EditText)view.findViewById(R.id.repassword);
        radioGroup=view.findViewById(R.id.gender);
        radioMale=view.findViewById(R.id.male);
        radioFemale=view.findViewById(R.id.female);
        textDate= view.findViewById(R.id.date);
        city=view.findViewById(R.id.city);
        textBirth=view.findViewById(R.id.birth);
        oldDrawable=imageView.getDrawable();
        textErrorGender=view.findViewById(R.id.textErrorGender);
        textErrorCity=view.findViewById(R.id.textErrorCity);
        button=view.findViewById(R.id.registar);
        Bundle bundle=getArguments();
        choose=bundle.getString("choose");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                stringGender =((RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringCity=city.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(container.getContext(),android.R.style.Theme_Holo_Dialog_MinWidth
                        ,onDateSetListener,year,month+1,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        onDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                years=year;
                textBirth.setText(day +" / "+ month +" / "+ year);
                stringDate=textBirth.getText().toString();

            }
        };
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (password.length() <= 7) {
                    password.setError("Please enter a strong password!");
                    password.requestFocus();
                } else{

                    stringPassword=password.getText().toString();
                }

            }



            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        rePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (rePassword.getText().toString().equals(password.getText().toString()) ) {

                } else if (rePassword.getText().toString().length() == 0 || rePassword.getText().toString().equals(null) || rePassword.getText().toString() != password.getText().toString()|| rePassword.length()<7) {
                    rePassword.setError("Please enter same password!");
                    rePassword.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isValidEmail(email.getText().toString())){

                }else{
                    email.setError("Invalid Email Address");
                    email.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (phone.getVisibility() == View.VISIBLE) {
            phone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (phone.length() == 10) {
                        if (phone.getText().toString().substring(0, 3).equals("079") || phone.getText().toString().substring(0, 3).equals("078") || phone.getText().toString().substring(0, 3).equals("077")) {


                        } else {
                            phone.setError("Phone Number unavailable!");
                            phone.requestFocus();
                        }
                    } else if (phone.length() < 10) {
                        phone.setError("You entered incorrect!");
                        phone.requestFocus();
                    } else {
                        phone.setError("You entered incorrect!");
                        phone.requestFocus();
                    }
                    mRootRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("Teacher").hasChild(phone.getText().toString())) {
                                j = 1;
                                phone.setError("Phone Number is used!");
                                phone.setFocusable(true);
                                phone.requestFocus();
                            } else {
                                j = 0;

                            }
                        }


                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose.equals("Teacher")){
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                if (firstName.getText().toString().equals("")||firstName.getText().toString().isEmpty()) {
                    firstName.setError("First Name Should not be blank");
                    firstName.setFocusable(true);
                    firstName.requestFocus();

                }
                else if(lastNmae.getText().toString().equals("")||lastNmae.getText().toString().isEmpty()){
                    lastNmae.setError("Last Name Should not be blank");
                    lastNmae.setFocusable(true);
                    lastNmae.requestFocus();
                }

                else if(textBirth.getText().toString().equals("")){
                    textDate.setError("Please Select Date!");
                    textDate.setFocusable(true);
                    textDate.requestFocus();
                }else if(year-7<years){
                    textDate.setError("Please Select current Date!");
                    textDate.requestFocus();
                }
                else if (phone.getText().toString().equals("")) {
                    phone.setError("Phone Number is required!");
                    phone.setFocusable(true);
                    phone.requestFocus();
                } else if (phone.getText().toString().length() < 10) {
                    phone.setError("Phone Number unavailable!");
                    phone.setFocusable(true);
                    phone.requestFocus();
                }else if(j==1){
                    phone.setError("Phone Number is used!");
                    phone.setFocusable(true);
                    phone.requestFocus();
                }

                else if (rePassword.getText().toString().equals("")) {
                    rePassword.setError("Please enter same password!");
                    rePassword.setFocusable(true);
                    rePassword.requestFocus();
                }
                else if(!radioMale.isChecked()&&!radioFemale.isChecked()){
                    textErrorGender.setText("Please tell us your gender!");
                    textErrorGender.setVisibility(View.VISIBLE);
                }
                else if(stringCity.equals("")){
                    button.setError("Please choose your City!");
                    textErrorCity.setText("Please choose your City!");
                    textErrorCity.setVisibility(View.VISIBLE);
                }
                else if(imageView.getDrawable() == oldDrawable){
                    button.setError("Choose your profile picture!");
                    button.setFocusable(true);
                    button.requestFocus();


                }else{
                    auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                            }else{
                                Toast.makeText(getActivity(), "Sign Up Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Bundle bundle=getArguments();
                    choose=bundle.getString("choose");
                    bundle.putString("name",firstName.getText().toString()+" "+lastNmae.getText().toString());
                    bundle.putString("phone",phone.getText().toString());
                    bundle.putString("email",email.getText().toString());
                    bundle.putString("password",stringPassword);
                    bundle.putString("gender",stringGender);
                    bundle.putString("date",stringDate);
                    bundle.putString("city",stringCity);
                    bundle.putString("image",imageUrl);
                    bundle.putString("choose",choose);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentTeClass fragmentTeClass = new FragmentTeClass();
                    fragmentTeClass.setArguments(bundle);
                    fragmentTransaction.replace(R.id.frag, fragmentTeClass);
                    fragmentTransaction.commit();

                }

            }else if(choose.equals("Student")){
                    Calendar calendar=Calendar.getInstance();
                    int year=calendar.get(Calendar.YEAR);
                    if (firstName.getText().toString().equals("")||firstName.getText().toString().isEmpty()) {
                        firstName.setError("First Name Should not be blank");
                        firstName.setFocusable(true);
                        firstName.requestFocus();

                    }
                    else if(lastNmae.getText().toString().equals("")||lastNmae.getText().toString().isEmpty()){
                        lastNmae.setError("Last Name Should not be blank");
                        lastNmae.setFocusable(true);
                        lastNmae.requestFocus();
                    }

                    else if(textBirth.getText().toString().equals("")){
                        textDate.setError("Please Select Date!");
                        textDate.setFocusable(true);
                        textDate.requestFocus();
                    }else if(year-7<years){
                        textDate.setError("Please Select current Date!");
                        textDate.requestFocus();
                    }
                    else if (phone.getText().toString().equals("")) {
                        phone.setError("Phone Number is required!");
                        phone.setFocusable(true);
                        phone.requestFocus();
                    } else if (phone.getText().toString().length() < 10) {
                        phone.setError("Phone Number unavailable!");
                        phone.setFocusable(true);
                        phone.requestFocus();
                    }else if(j==1){
                        phone.setError("Phone Number is used!");
                        phone.setFocusable(true);
                        phone.requestFocus();}

                    else if (rePassword.getText().toString().equals("")) {
                        rePassword.setError("Please enter same password!");
                        rePassword.setFocusable(true);
                        rePassword.requestFocus();
                    }
                    else if(!radioMale.isChecked()&&!radioFemale.isChecked()){
                        textErrorGender.setText("Please tell us your gender!");
                        textErrorGender.setVisibility(View.VISIBLE);
                    }
                    else if(stringCity.equals("")){
                        button.setError("Please choose your City!");
                        textErrorCity.setText("Please choose your City!");
                        textErrorCity.setVisibility(View.VISIBLE);
                    }
                    else if(imageView.getDrawable() == oldDrawable){
                        button.setError("Choose your profile picture!");
                        button.setFocusable(true);
                        button.requestFocus();


                    }else{
                        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                }else{
                                    Toast.makeText(getActivity(), "Sign Up Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Bundle bundle=getArguments();
                        choose=bundle.getString("choose");
                        final ProgressDialog pd = new ProgressDialog(getActivity());
                        pd.setTitle("Signing Up...");
                        pd.show();
                        Firebase firebase =mRootRef.child("Student").child(phone.getText().toString());
                        Student student=new Student();
                        student.setName(firstName.getText().toString()+" "+lastNmae.getText().toString());
                        student.setChoose(choose);
                        student.setPhone(phone.getText().toString());
                        student.setDate(stringDate);
                        student.setChoose(choose);
                        student.setPassword(stringPassword);
                        student.setGender(stringGender);
                        student.setEmail(email.getText().toString());
                        student.setCity(stringCity);
                        student.setImage(imageUrl);
                        firebase.setValue(student);
                        Intent intent=new Intent(getActivity(),LogIn.class);
                        intent.putExtra("Password",bundle.getString("Password"));
                        intent.putExtra("Phone",phone.getText().toString());
                        startActivityForResult(intent,1);

                }}
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== Activity.RESULT_OK && data!=null && data.getData()!=null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            uploadPicture();
        }    }
    private void choosePicture() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Uploading Image ");
        pd.show();
        final String randonKey = UUID.randomUUID().toString();
        riversRef = storageReference.child("images/"+randonKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(getView(),"Image Uploded.",Snackbar.LENGTH_LONG).show();
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageUrl= String.valueOf(uri);
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent=(100.00 *snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        pd.setMessage("Uploading : "+(int)progressPercent +" %");

                    }
                });
    }
    private boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
