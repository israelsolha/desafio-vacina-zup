package com.israelsolha.vacinas.repositories;

import com.israelsolha.vacinas.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, UUID> {

    Vaccine findByName(final String name);
}
