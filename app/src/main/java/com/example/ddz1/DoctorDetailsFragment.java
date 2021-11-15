package com.example.ddz1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.ddz1.api.response.Doctor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorDetailsFragment extends Fragment {

    private ImageView avatar;
    private TextView fio;
    private TextView specialty;
    private AppCompatButton appointmentMakeBtn;
    private TextView workExperienceDescription;
    private TextView servicesDescription;

    Doctor doctor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        doctor = DataContainer.getSelectedDoctor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_details, container, false);

        avatar = view.findViewById(R.id.avatar);
        fio = view.findViewById(R.id.fio);
        specialty = view.findViewById(R.id.specialty);
        appointmentMakeBtn = view.findViewById(R.id.appointmentMakeBtn);
        workExperienceDescription = view.findViewById(R.id.workExperienceDescription);
        servicesDescription = view.findViewById(R.id.servicesDescription);

        if (!doctor.getPhoto().equals("-1"))
            avatar.setImageBitmap(Utils.decodeBase64(doctor.getPhoto()));

        fio.setText(doctor.getName());

        if (!Utils.isNullOrEmptyString(doctor.getQualification())) {
            specialty.setText(doctor.getQualification());
            specialty.setVisibility(View.VISIBLE);
        }
        if (!Utils.isNullOrEmptyString(doctor.getDesc())) {
            workExperienceDescription.setText(doctor.getDesc());

            view.<TextView>findViewById(R.id.workExperienceDescriptionHeader).setVisibility(View.VISIBLE);
            workExperienceDescription.setVisibility(View.VISIBLE);
        }

        if (!Utils.isNullOrEmptyString(doctor.getServices())) {
            servicesDescription.setText(doctor.getServices());

            view.<TextView>findViewById(R.id.servicesDescriptionHeader).setVisibility(View.VISIBLE);
            servicesDescription.setVisibility(View.VISIBLE);
        }

        return view;
    }
}