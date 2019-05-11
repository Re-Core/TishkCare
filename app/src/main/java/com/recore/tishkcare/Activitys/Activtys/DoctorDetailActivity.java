package com.recore.tishkcare.Activitys.Activtys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recore.tishkcare.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetailActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;


    private String docName,docPhone,docMail,docLocation,specilty,startHour,endHour,gender,doctorId;
    private Button btnAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("Appointments");



        doctorId=getIntent().getStringExtra("doctorId");
//        docName= getIntent().getStringExtra("name");
//        docPhone= getIntent().getStringExtra("phone");
//        docMail= getIntent().getStringExtra("mail");
//
//        specilty=getIntent().getStringExtra("specialty");
//        docLocation= getIntent().getStringExtra("location");
//        startHour= getIntent().getStringExtra("startHour");
//        endHour= getIntent().getStringExtra("endHour");
//        gender=getIntent().getStringExtra("gender");






        btnAppointment=(Button)findViewById(R.id.btnSetAppointment);


        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }

    private void DoctorProfileDisplay(final CircleImageView profileImageView, final EditText Mail, final EditText mobile,
                                      final EditText edtDateOfbirth, final EditText education, final  EditText work) {

        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Doctors").child(doctorId);
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("image").exists()){

                        String image = dataSnapshot.child("image").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);

                    }if (dataSnapshot.child("education").exists()){
                        String educationString = dataSnapshot.child("education").getValue().toString();
                        education.setText(educationString);
                    }if (dataSnapshot.child("dateOfBirth").exists()){
                        String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                        edtDateOfbirth.setText(dateOfBirth);
                    } if (dataSnapshot.child("phone").exists()){
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        mobile.setText(phone);
                    }  if (dataSnapshot.child("work").exists()){
                        String workString= dataSnapshot.child("work").getValue().toString();
                        work.setText(workString);
                    }  if (dataSnapshot.child("email").exists()){
                        String emailAddress = dataSnapshot.child("email").getValue().toString();
                        Mail.setText(emailAddress);
                    }  if (dataSnapshot.child("bloodGroup").exists()){
                        String bloodG=dataSnapshot.child("bloodGroup").getValue().toString();
                        //BloodGroup.setText(bloodG);
                    }  if (dataSnapshot.child("gender").exists()){
                        String gender=dataSnapshot.child("gender").getValue().toString();
                        //Gender.setText(gender);
                    }  if (dataSnapshot.child("marriage").exists()){
                        String MarriageS=dataSnapshot.child("marriage").getValue().toString();
                       // Marriage.setText(MarriageS);
                    }  if (dataSnapshot.child("city").exists()){
                        String CityS=dataSnapshot.child("city").getValue().toString();
                     //   City.setText(CityS);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
