package com.israelsolha.vacinas.controllers;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.models.requests.VaccinationRequest;
import com.israelsolha.vacinas.models.response.VaccinationResponse;
import com.israelsolha.vacinas.repositories.VaccineRepository;
import com.israelsolha.vacinas.services.UserService;
import com.israelsolha.vacinas.services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.Normalizer;

@RestController @RequestMapping(value = "/vaccinations")
public class VaccinationController {

    @Autowired private VaccinationService vaccinationService;

    @Autowired private VaccineRepository vaccineRepository;

    @Autowired private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<VaccinationResponse> insert(
            @Valid @RequestBody VaccinationRequest vaccinationRequest) {
        String vaccineName = Normalizer
                .normalize(vaccinationRequest.getVaccineName(),
                        Normalizer.Form.NFD).replaceAll("\\p{Punct}", "")
                .toUpperCase();
        Vaccine vaccine = vaccineRepository.findByName(vaccineName);
        if (vaccine == null) {
            vaccine = new Vaccine(null, vaccineName);
            vaccine = vaccineRepository.save(vaccine);
        }
        User user =
                userService.find(vaccinationRequest.getEmail().toLowerCase());
        Vaccination vaccination = vaccinationRequest.toModel(vaccine, user);
        System.out.println(vaccination.getUser().getName());
        vaccination = vaccinationService.insert(vaccination);
        VaccinationResponse vaccinationResponse = vaccination.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaccinationResponse);
    }

}
