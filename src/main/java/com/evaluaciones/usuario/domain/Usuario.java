package com.evaluaciones.usuario.domain;

import com.evaluaciones.usuarioRol.domain.UsuarioRol;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    private boolean enabled = true;

    private String perfil;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
}
