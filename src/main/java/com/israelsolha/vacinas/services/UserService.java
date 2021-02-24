package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.repositories.UserRepository;
import com.israelsolha.vacinas.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service public class UserService {

    @Autowired private UserRepository repo;

    public User insert(User obj) {
        obj.setUuid(null);
        obj.setEmail(obj.getEmail().toLowerCase());
        return repo.save(obj);
    }

    public User find(String email) {
        email = email.toLowerCase();
        Optional<User> user = repo.findByEmail(email);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário com email inserido não foi encontrado"));
    }
}
