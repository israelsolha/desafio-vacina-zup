package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.Vaccine;
import com.israelsolha.vacinas.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository repo;

    public Vaccine insert(Vaccine obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Vaccine findOrInsert(String name){
        String vaccineName = Normalizer
                .normalize(name,
                        Normalizer.Form.NFD).replaceAll("\\p{Punct}", "")
                .toUpperCase();
        Vaccine vaccine = repo.findByName(vaccineName);
        if (vaccine == null) {
            vaccine = new Vaccine(null, vaccineName);
            vaccine = insert(vaccine);
        }
        return vaccine;
    }
}
