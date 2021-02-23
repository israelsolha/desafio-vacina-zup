package com.israelsolha.vacinas.prototypes;

import com.israelsolha.vacinas.models.requests.UserRequest;

import java.time.LocalDate;

public class UserRequestPrototype {

    private String name;
    private String email;
    private String cpf;
    private String birthDate;

    public UserRequestPrototype(String name, String email, String cpf, String birthDate) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public static UserRequestPrototype aUserRequestJson(){
        return new UserRequestPrototype("Israel","israel-solha@hotmail.com","89450209006", "1996-10-10");
    }

    public static UserRequest aUserRequest(){
        return new UserRequest("Israel","israel-solha@hotmail.com","89450209006", LocalDate.of(1996,10,10));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
