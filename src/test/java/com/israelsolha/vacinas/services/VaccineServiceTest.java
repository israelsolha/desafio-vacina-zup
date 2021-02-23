package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.repositories.VaccineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.israelsolha.vacinas.prototypes.VaccinePrototype.aVaccine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VaccineServiceTest {

    @Mock
    private VaccineRepository vaccineRepository;

    @InjectMocks
    private VaccineService vaccineService;

    @Test
    public void insert() {
        Vaccine vaccine = aVaccine();
        when(vaccineRepository.save(any())).thenReturn(vaccine);
        Vaccine insertedVaccine = vaccineService.insert(vaccine);
        assertNotNull(insertedVaccine);
        assertEquals(insertedVaccine.getName(), vaccine.getName());
    }

}
