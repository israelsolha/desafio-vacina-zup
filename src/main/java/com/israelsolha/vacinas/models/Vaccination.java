package com.israelsolha.vacinas.models;

import com.israelsolha.vacinas.models.response.VaccinationResponse;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Vaccination {

    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
    @NotNull(message = "Preenchimento obrigatório")
    @PastOrPresent(message="Data de vacinação deve ser hoje ou antes")
    private LocalDate vaccinationDate;

    public Vaccination() {
    }

    public Vaccination(UUID uuid, User user, Vaccine vaccine, LocalDate vaccinationDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public VaccinationResponse toResponse() {
        return new VaccinationResponse(uuid,user.toResponse(),vaccine,vaccinationDate);
    }
}
