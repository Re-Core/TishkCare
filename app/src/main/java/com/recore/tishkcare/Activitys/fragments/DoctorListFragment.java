package com.recore.tishkcare.Activitys.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recore.tishkcare.Activitys.Adapter.DoctorAdapter;
import com.recore.tishkcare.Activitys.Model.Doctor;
import com.recore.tishkcare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorListFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private RecyclerView doctorRecycler;
    private DoctorAdapter mDoctorAdapter;
    private List<Doctor>mDoctorList;


    public DoctorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_doctor_list, container, false);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("Doctors");

        doctorRecycler=(RecyclerView)v.findViewById(R.id.doctorRv);

        doctorRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorRecycler.setAdapter(mDoctorAdapter);

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mDoctorList = new ArrayList<>();

                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    Doctor doctor =postSnapshot.getValue(Doctor.class);
                    mDoctorList.add(doctor);


                }

                mDoctorAdapter = new DoctorAdapter(getActivity(),mDoctorList);
                doctorRecycler.setAdapter(mDoctorAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
