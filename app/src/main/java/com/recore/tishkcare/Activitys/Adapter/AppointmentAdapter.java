package com.recore.tishkcare.Activitys.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.recore.tishkcare.Activitys.Activtys.AppointmentDetailActivity;
import com.recore.tishkcare.Activitys.Model.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder> {

    private Context mContext;
    private List<Appointment>mAppointmentList;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        mContext = context;
        mAppointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHolder appointmentHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }

    public class AppointmentHolder extends RecyclerView.ViewHolder{

        public AppointmentHolder(@NonNull View itemView) {
            super(itemView);

            int position=getAdapterPosition();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mContext, AppointmentDetailActivity.class);

                    mContext.startActivity(i);

                }
            });
        }
    }

}
