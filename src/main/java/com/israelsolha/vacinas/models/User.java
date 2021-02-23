package com.israelsolha.vacinas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.israelsolha.vacinas.models.response.UserResponse;
import com.israelsolha.vacinas.services.validations.UniqueField;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class User {


    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;
    @NotBlank(message="Preenchimento obrigatório")
    private String name;
    @Column(unique = true)
    @Email(message = "Email inválido")
    @NotBlank(message="Preenchimento obrigatório")
    private String email;
    @Column(unique = true)
    @CPF(message="CPF inválido")
    @NotBlank(message="Preenchimento obrigatório")
    private String cpf;
    @NotNull(message = "Preenchimento obrigatório")
    @PastOrPresent(message="Data de nascimento deve ser hoje ou antes")
    private LocalDate birthDate;
    private Long creationTimestamp = System.currentTimeMillis();


    @Deprecated
    public User() {
    }

    public User(UUID uuid, String name, String email, String cpf, LocalDate birthDate) {
        this.uuid = uuid;
        this.name = name;
        this.email = email.toLowerCase();
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserResponse toResponse(){
        return new UserResponse(uuid,name,email);
    }
}
