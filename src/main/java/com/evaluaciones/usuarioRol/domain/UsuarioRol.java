package com.evaluaciones.usuarioRol.domain;

import com.evaluaciones.rol.domain.Rol;
import com.evaluaciones.usuario.domain.Usuario;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne
    private Rol rol;
}
