package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository repo;

    public Vaccine insert(Vaccine obj) {
        obj.setId(null);
        return repo.save(obj);
    }
}
