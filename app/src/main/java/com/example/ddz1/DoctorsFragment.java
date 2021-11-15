package com.example.ddz1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddz1.adapters.DoctorsAdapter;
import com.example.ddz1.api.Simplex48ApiRequests;
import com.example.ddz1.api.response.Doctor;
import com.example.ddz1.api.response.Specialty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DoctorsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageView topIcon;
    TextView topText;
    Spinner specializations;
    RecyclerView doctorCards;
    Button atMainBtn;

    List<Specialty> specialtiesList;
    List<Doctor> doctorList;

    ArrayAdapter<String> spinnerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, container, false);

        topIcon = view.findViewById(R.id.topIcon);
        topText = view.findViewById(R.id.topText);
        specializations = view.findViewById(R.id.specializations);
        doctorCards = view.findViewById(R.id.doctorCards);
        atMainBtn = view.findViewById(R.id.atMainBtn);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        doctorCards.setLayoutManager(manager);

        loadSpecialties();
        loadDoctors();

        specializations.setOnItemSelectedListener(this);
        return view;
    }

    private void loadSpecialties() {
        new Thread(() -> {
            if (DataContainer.getSpecialties() == null) {
                try {
                    specialtiesList = Simplex48ApiRequests.getSpecialties(5, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                    specialtiesList = new ArrayList<>();
                    specialtiesList.add(new Specialty(0, "Не удалось загрузить специальности"));
                }
                DataContainer.setSpecialties(specialtiesList);
            } else {
                specialtiesList = DataContainer.getSpecialties();
            }
            getActivity().runOnUiThread(this::createSpinnerAdapter);

        }).start();
    }

    private void createSpinnerAdapter() {
        List<String> list = new ArrayList<>();
        for (Specialty specialty : specialtiesList) {
            list.add(specialty.getName());
        }

        spinnerAdapter = new ArrayAdapter<>(getContext(),
                R.layout.spinner_item,
                list);
        specializations.setAdapter(spinnerAdapter);
    }

    private void loadDoctors() {
        new Thread(() -> {
            if (DataContainer.getDoctors() == null) {
                try {
                    doctorList = Simplex48ApiRequests.getDoctors(5, 1);
                } catch (IOException e) {
                    doctorList = Collections.emptyList();
                }
                DataContainer.setDoctors(doctorList);
            } else {
                doctorList = DataContainer.getDoctors();
            }
            getActivity().runOnUiThread(() -> showDoctors(specialtiesList.get(0)));
        }).start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (doctorList != null)
            showDoctors(specialtiesList.get(position));
    }

    private void showDoctors(Specialty specialty) {
        doctorCards.setAdapter(
                new DoctorsAdapter(filterDoctors(specialty.getId()),
                        spinnerAdapter.getContext()));
    }

    @NonNull
    private List<Doctor> filterDoctors(Integer specializationId) {
        List<Doctor> filteredDoctors = new LinkedList<>();
        for (Doctor doctor : doctorList) {
            if (doctor.getDOCT_IDs().contains(specializationId)) {
                filteredDoctors.add(doctor);
            }
        }
        return filteredDoctors;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}