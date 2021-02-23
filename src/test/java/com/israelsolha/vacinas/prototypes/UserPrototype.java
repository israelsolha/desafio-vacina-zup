package com.israelsolha.vacinas.prototypes;

import com.israelsolha.vacinas.models.User;

import java.time.LocalDate;

public class UserPrototype {

    public static User aUser() {
        LocalDate localDate = LocalDate.of(1996, 10, 10);
        return new User(null, "Israel", "israel-solha@hotmail.com", "89450209006", localDate);
    }
}
