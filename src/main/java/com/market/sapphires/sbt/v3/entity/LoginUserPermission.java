package com.market.sapphires.sbt.v3.entity;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginUserPermission {
    /** */
    USER_SHOW("USER_SHOW"),
    /** */
    USER_EDIT("USER_EDIT");

    private GrantedAuthority authority;

    LoginUserPermission(String authority) {
        this.authority = new SimpleGrantedAuthority(authority);
    }

    public static LoginUserPermission getByName(String name) {
        return Arrays.asList(LoginUserPermission.values()).stream()
                .filter(p -> p.getAuthority().toString().equals(name))
                .findFirst().get();
    }

}
