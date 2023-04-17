package com.evaluaciones.security.domain.incoming.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String username;

    private String password;
}
