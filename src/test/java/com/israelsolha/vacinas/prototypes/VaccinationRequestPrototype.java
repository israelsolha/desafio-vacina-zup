package com.israelsolha.vacinas.prototypes;

import com.israelsolha.vacinas.models.requests.VaccinationRequest;

public class VaccinationRequestPrototype {

    private String vaccineName;
    private String email;
    private String vaccinationDate;

    public VaccinationRequestPrototype(String vaccineName, String email, String vaccinationDate) {
        this.vaccineName = vaccineName;
        this.email = email;
        this.vaccinationDate = vaccinationDate;
    }

    public static VaccinationRequestPrototype aVaccinationRequestJson(){
        return new VaccinationRequestPrototype("coronavac", "israel-solha@hotmail.com", "25/01/2021");
    }

    public static VaccinationRequest aVaccinationRequest() {
        return new VaccinationRequest("coronavac", "israel-solha@hotmail.com", "25/01/2021");
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
