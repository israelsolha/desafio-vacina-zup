package com.israelsolha.vacinas.repositories;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.models.Vaccine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ValidationException;
import java.time.LocalDate;

import static com.israelsolha.vacinas.prototypes.VaccinationPrototype.aVaccination;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest public class VaccinationRepositoryTest {

    @Autowired private VaccinationRepository vaccinationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private VaccineRepository vaccineRepository;

    @Test public void testIfVaccinationIsSaved() {
        Vaccination vaccination = aVaccination();
        Vaccination insertedVaccine = vaccinationRepository.save(vaccination);
        assertNotNull(insertedVaccine);
        assertEquals(insertedVaccine.getVaccinationDate(),
                vaccination.getVaccinationDate());
        assertNotNull(vaccination.getUuid());
    }

    @Test public void testIfValidationsAreWorking() {
        Vaccination vaccination = aVaccination();
        User user = vaccination.getUser();
        Vaccine vaccine = vaccination.getVaccine();
        vaccination.setVaccinationDate(LocalDate.of(2022, 10, 10));
        userRepository.save(user);
        vaccineRepository.save(vaccine);
        assertThrows(ValidationException.class,
                () -> vaccinationRepository.saveAndFlush(vaccination));
    }
}
