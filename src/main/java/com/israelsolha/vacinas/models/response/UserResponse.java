package com.israelsolha.vacinas.models.response;

import java.util.UUID;

public class UserResponse {

    private UUID uuid;
    private String name;
    private String email;

    @Deprecated public UserResponse() {
    }

    public UserResponse(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
