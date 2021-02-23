package com.israelsolha.vacinas.models.requests;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.services.validations.UniqueField;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class UserRequest {

    @NotBlank(message = "Preenchimento obrigatório")
    private String name;
    @UniqueField(fieldName = "email",domainClass = User.class)
    @Email(message = "Email inválido")
    @NotBlank(message = "Preenchimento obrigatório")
    private String email;
    @UniqueField(fieldName = "cpf",domainClass = User.class)
    @CPF(message="CPF inválido")
    @NotBlank(message = "Preenchimento obrigatório")
    private String cpf;
    @NotNull(message = "Preenchimento obrigatório")
    @Past(message="Data de nascimento deve ser no passado")
    private LocalDate birthDate;

    public UserRequest() {
    }

    public UserRequest(String name, String email, String cpf, LocalDate birthDate) {
        this.name = name;
        this.email = email.toLowerCase();
        this.cpf = cpf;
        this.birthDate = birthDate;
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
        this.email = email.toLowerCase();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User toModel() {
        return new User(null,name,email,cpf, birthDate);
    }
}
