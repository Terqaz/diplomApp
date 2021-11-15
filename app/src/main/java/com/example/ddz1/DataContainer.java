package com.example.ddz1;

import com.example.ddz1.api.response.Doctor;
import com.example.ddz1.api.response.Specialty;

import java.util.List;

public class DataContainer {
    private static List<Doctor> doctors;
    private static Doctor selectedDoctor;
    public static List<Specialty> specialties;

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static void setDoctors(List<Doctor> doctors) {
        DataContainer.doctors = doctors;
    }

    public static Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public static void setSelectedDoctor(Doctor selectedDoctor) {
        DataContainer.selectedDoctor = selectedDoctor;
    }

    public static List<Specialty> getSpecialties() {
        return specialties;
    }

    public static void setSpecialties(List<Specialty> specialties) {
        DataContainer.specialties = specialties;
    }
}
