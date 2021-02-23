package com.israelsolha.vacinas.repositories;

import com.israelsolha.vacinas.models.Vaccine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ValidationException;

import static com.israelsolha.vacinas.prototypes.VaccinePrototype.aVaccine;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VaccineRepositoryTest {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Test
    public void testIfVaccineIsSaved() {
        Vaccine vaccine = vaccineRepository.save(aVaccine());
        assertNotNull(vaccine);
        assertEquals(aVaccine().getName(), vaccine.getName());
        assertNotNull(vaccine.getId());
    }

    @Test
    public void testIfValidationsAreWorking() {
        Vaccine vaccine = vaccineRepository.save(aVaccine());
        vaccine.setName("");
        assertThrows(ValidationException.class, () -> vaccineRepository.saveAndFlush(vaccine));
    }
}
