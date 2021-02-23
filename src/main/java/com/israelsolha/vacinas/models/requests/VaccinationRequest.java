package com.israelsolha.vacinas.models.requests;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.services.validations.LocalDateCheck;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VaccinationRequest {

    @NotBlank(message = "Preenchimento obrigatório") private String vaccineName;
    @Email(message = "Email inválido")
    @NotBlank(message = "Preenchimento obrigatório") private String email;
    @LocalDateCheck private String vaccinationDate;

    private LocalDate vaccinationLocalDate;

    public VaccinationRequest() {
    }

    public VaccinationRequest(String vaccineName, String email,
                              String vaccinationDate) {
        this.vaccineName = vaccineName;
        this.email = email;
        this.vaccinationDate = vaccinationDate;
        this.vaccinationLocalDate = LocalDate.parse(vaccinationDate,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public LocalDate getVaccinationLocalDate() {
        return vaccinationLocalDate;
    }

    public void setVaccinationLocalDate(LocalDate vaccinationLocalDate) {
        this.vaccinationLocalDate = vaccinationLocalDate;
    }

    public Vaccination toModel(Vaccine vaccine, User user) {
        vaccinationLocalDate = LocalDate.parse(vaccinationDate,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new Vaccination(null, user, vaccine, vaccinationLocalDate);
    }
}
