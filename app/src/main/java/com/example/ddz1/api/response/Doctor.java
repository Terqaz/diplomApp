package com.example.ddz1.api.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Doctor {
    public int id;
    public String name;
    public String photo;                 // фото врача в формате BASE64, «-1» - если фото отсутствует
    public String desc;                  // описание
    public String specialization;        // специализация
    public String qualification;         // квалификация
    public String services;              // оказываемые услуги
    public List<Integer> DOCT_IDs; // список идентификаторов специальностей врача
}
