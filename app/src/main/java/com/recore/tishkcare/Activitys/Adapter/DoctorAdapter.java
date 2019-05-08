package com.recore.tishkcare.Activitys.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recore.tishkcare.Activitys.Activtys.DoctorDetailActivity;
import com.recore.tishkcare.Activitys.Model.Doctor;
import com.recore.tishkcare.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context mContext;
    private List<Doctor>mDoctorList;


    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        mContext = context;
        mDoctorList = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_doctor,viewGroup,false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.doctorName.setText(mDoctorList.get(i).getName());
        viewHolder.doctorMobile.setText(mDoctorList.get(i).getPhone());
        viewHolder.doctorCity.setText(mDoctorList.get(i).getEmail());
        viewHolder.doctorType.setText(mDoctorList.get(i).getPassword());


    }

    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView doctorName,doctorMobile,doctorType,doctorCity;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            doctorName=(TextView)itemView.findViewById(R.id.txtDoctorName);
            doctorMobile=(TextView)itemView.findViewById(R.id.txtDoctorPhone);
            doctorType=(TextView)itemView.findViewById(R.id.txtDoctorType);
            doctorCity=(TextView)itemView.findViewById(R.id.txtDoctorCity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Intent i = new Intent(mContext, DoctorDetailActivity.class);
                    i.putExtra("name",mDoctorList.get(position).getName());
                    i.putExtra("mail",mDoctorList.get(position).getEmail());
                    i.putExtra("phone",mDoctorList.get(position).getPhone());
                    mContext.startActivity(i);

                }
            });

        }
    }

}
