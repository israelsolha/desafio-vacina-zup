package com.israelsolha.vacinas.prototypes;

import com.israelsolha.vacinas.models.Vaccination;

import java.time.LocalDate;

import static com.israelsolha.vacinas.prototypes.UserPrototype.aUser;
import static com.israelsolha.vacinas.prototypes.VaccinePrototype.aVaccine;

public class VaccinationPrototype {
    public static Vaccination aVaccination() {
        LocalDate localDate = LocalDate.of(2021, 01, 10);
        return new Vaccination(null, aUser(), aVaccine(), localDate);
    }
}
