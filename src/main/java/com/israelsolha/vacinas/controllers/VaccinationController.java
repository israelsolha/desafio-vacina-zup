package com.israelsolha.vacinas.controllers;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.models.requests.VaccinationRequest;
import com.israelsolha.vacinas.models.response.VaccinationResponse;
import com.israelsolha.vacinas.services.UserService;
import com.israelsolha.vacinas.services.VaccinationService;
import com.israelsolha.vacinas.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController @RequestMapping(value = "/vaccinations")
public class VaccinationController {

    @Autowired private VaccinationService vaccinationService;

    @Autowired private VaccineService vaccineService;

    @Autowired private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<VaccinationResponse> insert(
            @Valid @RequestBody VaccinationRequest vaccinationRequest) {
        Vaccine vaccine =
                vaccineService.findOrInsert(vaccinationRequest.getVaccineName());
        User user =
                userService.find(vaccinationRequest.getEmail().toLowerCase());
        Vaccination vaccination = vaccinationRequest.toModel(vaccine, user);
        vaccination = vaccinationService.insert(vaccination);
        VaccinationResponse vaccinationResponse = vaccination.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaccinationResponse);
    }

}
