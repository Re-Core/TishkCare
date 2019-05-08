package com.recore.tishkcare.Activitys.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recore.tishkcare.Activitys.Model.Patient;
import com.recore.tishkcare.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference patientRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String currentUserId;
    private Patient currentPateint;
    private Dialog popupEditProfile;
    private ImageView edtProfile;

    private TextView txtName,txtMail,txtGender,txtBloodGroup,txtEducation,txtMobile,
            txtWork,txtMarriage,txtDateOfBirth,txtCity;
    private String bloodGroup="",education="",work="",gender="",marriage="",dateOfBirth="",city="";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        patientRef=mFirebaseDatabase.getReference("Patients");

        txtName=(TextView)v.findViewById(R.id.name);
        txtMail=(TextView)v.findViewById(R.id.email);
        txtMobile=(TextView)v.findViewById(R.id.mobileNumber);

        txtBloodGroup=(TextView)v.findViewById(R.id.blood_group);
        txtEducation=(TextView)v.findViewById(R.id.education);
        txtWork=(TextView)v.findViewById(R.id.occupation);
        txtGender=(TextView)v.findViewById(R.id.gender);
        txtDateOfBirth=(TextView)v.findViewById(R.id.dob);
        txtMarriage=(TextView)v.findViewById(R.id.marriage);
        txtCity=(TextView)v.findViewById(R.id.location);
        edtProfile=(ImageView)v.findViewById(R.id.edit);

        txtMarriage.setText(marriage);
        txtGender.setText(gender);
        txtEducation.setText(education);
        txtBloodGroup.setText(bloodGroup);
        txtDateOfBirth.setText(dateOfBirth);
        txtWork.setText(work);
        txtCity.setText(city);

        edtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopUp();

            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUserId=mCurrentUser.getUid();

        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(currentUserId)) {
                    currentPateint = dataSnapshot.child(currentUserId).getValue(Patient.class);
                    //Prelevents.currentOnlineUser = currentUser;
                }

                //Toast.makeText(getActivity(), currentPateint.getName(), Toast.LENGTH_SHORT).show();
                txtName.setText(currentPateint.getName());
                txtMail.setText(currentPateint.getEmail());
                txtMobile.setText(currentPateint.getPhone());
                //txtDepartment.setText(currentUser.getUserDepartment());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (bloodGroup.isEmpty()||gender.isEmpty()||dateOfBirth.isEmpty()||city.isEmpty()){

            showMessage("please fill mandatory data (blood group,gender,DOB,City)");


        }


    }

//    private void updateOnlyUserInfo() {
//
//
//        HashMap<String, Object> userMap = new HashMap<>();
//
//        if (!edtPopUpUserName.getText().toString().isEmpty() && !edtPopUpUserMail.getText().toString().isEmpty() &&
//                !edtPopUpUserPhone.getText().toString().isEmpty() && !edtPopUpUserDepartment.getText().toString().isEmpty()) {
//
//            userMap.put("username", edtPopUpUserName.getText().toString());
//            userMap.put("userMail", edtPopUpUserMail.getText().toString());
//            userMap.put("userPhoneNumber", edtPopUpUserPhone.getText().toString());
//            userMap.put("userDepartment", edtPopUpUserDepartment.getText().toString());
//
//            userNodeDatabaseRefrence.child(mCurrentUser.getUid()).updateChildren(userMap);
//            showMessage("Profile info updated successfully");
//
//        } else {
//            showMessage("Confirm All field");
//        }
//
//    }

    private void showMessage(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void initPopUp(){

        popupEditProfile = new Dialog(getContext());
        popupEditProfile.setContentView(R.layout.popup_edit_profile);
        popupEditProfile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupEditProfile.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popupEditProfile.getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL;


        popupEditProfile.show();

    }



}
