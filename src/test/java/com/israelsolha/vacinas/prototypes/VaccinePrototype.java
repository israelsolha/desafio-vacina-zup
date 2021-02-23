package com.israelsolha.vacinas.prototypes;

import com.israelsolha.vacinas.models.Vaccine;

public class VaccinePrototype {
    public static Vaccine aVaccine() {
        return new Vaccine(null, "coronavac");
    }
}
