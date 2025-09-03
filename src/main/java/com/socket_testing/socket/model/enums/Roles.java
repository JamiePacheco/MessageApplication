package com.socket_testing.socket.model.enums;

public enum Roles  {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Roles(String role) {
        this.role = role;
    }
}
