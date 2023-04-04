package com.evaluaciones.usuario.domain.incoming;

import com.evaluaciones.usuario.domain.Usuario;
import com.evaluaciones.usuarioRol.domain.UsuarioRol;

import java.util.Set;

public interface UsuarioLogic {

    Usuario saveUser(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    Usuario getUser(String username) throws Exception;

    void deleteUser(Long id) throws Exception;
}
