package com.evaluaciones.rol.domain;

import com.evaluaciones.usuarioRol.domain.UsuarioRol;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    private Long rolId;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

}
