package com.example.ddz1.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddz1.DataContainer;
import com.example.ddz1.DoctorDetailsFragment;
import com.example.ddz1.MainActivity;
import com.example.ddz1.R;
import com.example.ddz1.Utils;
import com.example.ddz1.api.response.Doctor;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsHolder> {

    List<Doctor> doctorList;
    private final Context mContext;

    public DoctorsAdapter(List<Doctor> doctorList, Context mContext) {
        this.doctorList = doctorList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DoctorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_doctor_card, parent,false);
        return new DoctorsHolder(v);
    }

    public class DoctorsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView fio;
        TextView specialization;
        TextView qualification;
        Button makeAppointmentBtn;
        Button infoBtn;

        public DoctorsHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            fio = itemView.findViewById(R.id.fio);

            specialization = itemView.findViewById(R.id.specialization);
            qualification = itemView.findViewById(R.id.qualification);

            makeAppointmentBtn = itemView.findViewById(R.id.makeAppointmentBtn);
            infoBtn = itemView.findViewById(R.id.infoBtn);

            infoBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.infoBtn:
                    showDoctorDetails();
                case R.id.makeAppointmentBtn:
                    break;
            }
        }

        private void showDoctorDetails() {
            Fragment details = new DoctorDetailsFragment();
            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putInt("doctorPosition", getAdapterPosition());
            DataContainer.setSelectedDoctor(doctorList.get(getAdapterPosition()));
            details.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,  details).addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsHolder holder, int position) {
        Doctor doctor = doctorList.get(position);

        if (!doctor.getPhoto().equals("-1"))
            holder.avatar.setImageBitmap(Utils.decodeBase64(doctor.getPhoto()));

        holder.fio.setText(doctor.getName());

        if (!Utils.isNullOrEmptyString(doctor.getQualification())) {
            holder.qualification.setText(doctor.getQualification());
            holder.qualification.setVisibility(View.VISIBLE);
        }
        if (!Utils.isNullOrEmptyString(doctor.getSpecialization())) {
            holder.specialization.setText(doctor.getSpecialization());
            holder.specialization.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
}
