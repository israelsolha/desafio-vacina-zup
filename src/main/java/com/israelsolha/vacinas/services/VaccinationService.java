package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.models.Vaccination;
import com.israelsolha.vacinas.repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository repo;

    @Autowired
    private UserService userService;

    public Vaccination insert(Vaccination obj) {
        User user = userService.find(obj.getUser().getEmail());
        obj.setUuid(null);
        return repo.save(obj);
    }
}
