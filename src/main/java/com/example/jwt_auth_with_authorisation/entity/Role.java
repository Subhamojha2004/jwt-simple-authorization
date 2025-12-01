package com.example.jwt_auth_with_authorisation.entity;

import java.util.Set;

public enum Role {
    USER(Set.of(Permissions.USER_READ)),
    ADMIN(Set.of(Permissions.USER_READ,Permissions.USER_DELETE,Permissions.USER_WRITE));
    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
