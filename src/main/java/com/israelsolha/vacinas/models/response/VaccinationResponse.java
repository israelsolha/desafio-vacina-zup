package com.israelsolha.vacinas.models.response;

import com.israelsolha.vacinas.models.Vaccine;

import java.time.LocalDate;
import java.util.UUID;

public class VaccinationResponse {

    private UUID uuid;
    private UserResponse user;
    private Vaccine vaccine;
    private LocalDate vaccinationDate;

    public VaccinationResponse() {
    }

    public VaccinationResponse(UUID uuid, UserResponse user, Vaccine vaccine,
                               LocalDate vaccinationDate) {
        this.uuid = uuid;
        this.user = user;
        this.vaccine = vaccine;
        this.vaccinationDate = vaccinationDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
