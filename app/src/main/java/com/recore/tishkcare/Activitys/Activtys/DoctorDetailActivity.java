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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recore.tishkcare.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetailActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase; // email -> _email
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;


    private String docName,docPhone,docMail,docLocation,specilty,startHour,endHour,gender,doctorId,doctorImg;
    private TextView txtDocName,txtDocPhone,txtDocSpecialty,txtLocation;
    private Button btnAppointment;
    private CircleImageView doctorImgC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("Appointments");

        doctorImgC=(CircleImageView)findViewById(R.id.OimgUser);
        txtDocName=(TextView)findViewById(R.id.OtxtUserName);
        txtDocSpecialty=(TextView)findViewById(R.id.txtSpeciality);
        txtLocation=(TextView)findViewById(R.id.txtLocation);
        txtDocPhone=(TextView)findViewById(R.id.txtPhone);

        doctorId=getIntent().getStringExtra("doctorId");
        doctorImg=getIntent().getStringExtra("doctorImg");


        docName= getIntent().getStringExtra("name");
        docPhone= getIntent().getStringExtra("phone");
        docMail= getIntent().getStringExtra("mail");
        specilty=getIntent().getStringExtra("specialty");
        docLocation= getIntent().getStringExtra("location");



        Glide.with(getApplicationContext()).load(doctorImg).into(doctorImgC);
        txtDocName.setText(docName);
        txtDocPhone.setText(docPhone);
        txtDocSpecialty.setText(specilty);
        txtLocation.setText(docLocation);



        btnAppointment=(Button)findViewById(R.id.btnSetAppointment);


        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              addAppointment();

            }
        });



    }

    private void addAppointment(){

        String saveCurrentDate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat =new SimpleDateFormat("MMM dd,yyyy");

        saveCurrentDate=dateFormat.format(calendar.getTime());


        SimpleDateFormat timeFormat =new SimpleDateFormat("HH,mm,ss a");
        saveCurrentTime=dateFormat.format(calendar.getTime());

        final DatabaseReference AppointmentsListRef = FirebaseDatabase.getInstance().getReference().child("Appointments");

        final HashMap<String,Object>appointmentMap = new HashMap<>();

        appointmentMap.put("doctorId",doctorId);
        appointmentMap.put("doctorName",docName);
        appointmentMap.put("patientName",FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        appointmentMap.put("date",saveCurrentDate);
        appointmentMap.put("time",saveCurrentTime);


        AppointmentsListRef.child("Patient View").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).updateChildren(appointmentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    AppointmentsListRef.child("Doctor View").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).updateChildren(appointmentMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(DoctorDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(DoctorDetailActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }

//    private void addToCartList() {
//
//        String saveCurrentDate,saveCurrentTime;
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat =new SimpleDateFormat("MMM dd,yyyy");
//
//        saveCurrentDate=dateFormat.format(calendar.getTime());
//
//
//        SimpleDateFormat timeFormat =new SimpleDateFormat("HH,mm,ss a");
//        saveCurrentTime=dateFormat.format(calendar.getTime());
//
//        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList");
//
//        final HashMap<String,Object>cartMap = new HashMap<>();
//
//        cartMap.put("pid",productId);
//        cartMap.put("pname",nameProductDetail.getText().toString());
//        cartMap.put("totalPrice",priceProductDetail.getText().toString());
//        cartMap.put("quantity",quantityButton.getNumber());
//        cartMap.put("date",saveCurrentDate);
//        cartMap.put("time",saveCurrentTime);
//        cartMap.put("discount",null);
//
//        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).
//                child("Products").child(productId).updateChildren(cartMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone()).
//                                    child("Products").child(productId).updateChildren(cartMap)
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                Toast.makeText(ProductDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
//
//                                                Intent i = new Intent(ProductDetailActivity.this,HomeActivity.class);
//                                                startActivity(i);
//                                                finish();
//                                            }
//                                        }
//                                    });
//                        }
//                    }
//                });
//
//
//
//    }


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
