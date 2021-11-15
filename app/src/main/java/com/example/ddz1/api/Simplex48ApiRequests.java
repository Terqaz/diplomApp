package com.example.ddz1.api;

import com.example.ddz1.api.response.Doctor;
import com.example.ddz1.api.response.Specialty;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class Simplex48ApiRequests extends Simplex48Api {

    public static List<Specialty> getSpecialties(Integer medOrgId, Integer braId) throws IOException {

        Request request = new Request.Builder()
                .url(Simplex48Api.getGeneralUrlBuilder()
                        .addPathSegment("allSpecForBranch")
                        .addPathSegment(medOrgId.toString())
                        .addPathSegment(braId.toString())
                        .build()).build();

        return executeRequest(request, new TypeToken<List<Specialty>>() {});
    }

    public static List<Doctor> getDoctors(Integer medOrgId, Integer braId) throws IOException {

        Request request = new Request.Builder()
                .url(Simplex48Api.getGeneralUrlBuilder()
                        .addPathSegment("medicinfo")
                        .addPathSegment(medOrgId.toString())
                        .addPathSegment(braId.toString())
                        .build()).build();

        return executeRequest(request, new TypeToken<List<Doctor>>() {});
    }
}
