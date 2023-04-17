package com.evaluaciones.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
public class Auth implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
