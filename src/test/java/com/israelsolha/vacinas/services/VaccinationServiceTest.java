package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.controllers.exceptions.UserNotFoundError;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.repositories.VaccinationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.israelsolha.vacinas.prototypes.VaccinationPrototype.aVaccination;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) public class VaccinationServiceTest {

    @Mock private VaccinationRepository vaccinationRepository;

    @InjectMocks private VaccinationService vaccinationService;

    @Mock private UserService userService;

    @Test public void insert() {
        Vaccination vaccination = aVaccination();
        when(vaccinationRepository.save(any())).thenReturn(vaccination);
        when(userService.find(any())).thenReturn(vaccination.getUser());
        Vaccination insertedVaccination =
                vaccinationService.insert(vaccination);
        assertNotNull(insertedVaccination);
        assertEquals(insertedVaccination.getVaccinationDate(),
                vaccination.getVaccinationDate());
    }

    @Test public void checkEmailsNotRelatedToUserAreNotAllowed() {
        Vaccination vaccination = aVaccination();
        vaccination.getUser().setEmail("emaildoesnotexist@gmail.com");
        when(userService.find(any())).thenThrow(UserNotFoundError.class);
        assertThrows(UserNotFoundError.class,
                () -> vaccinationService.insert(vaccination));
    }

}
