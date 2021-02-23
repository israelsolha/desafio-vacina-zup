package com.israelsolha.vacinas.models.requests;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.repositories.VaccineRepository;
import com.israelsolha.vacinas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class VaccinationRequest {

    @Autowired
    private UserService userService;

    @Autowired
    private VaccineRepository vaccineRepository;

    @NotBlank(message = "Preenchimento obrigatório")
    private String vaccineName;
    @Email(message = "Email inválido")
    @NotBlank(message = "Preenchimento obrigatório")
    private String email;
    @NotNull(message = "Preenchimento obrigatório")
    @Past(message="Data de nascimento deve ser no passado")
    private LocalDate vaccinationDate;

    public VaccinationRequest() {
    }

    public VaccinationRequest(String vaccineName, String email, LocalDate vaccinationDate) {
        this.vaccineName = vaccineName;
        this.email = email;
        this.vaccinationDate = vaccinationDate;
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

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public Vaccination toModel(Vaccine vaccine, User user) {

        return new Vaccination(null,user,vaccine,vaccinationDate);
    }
}
