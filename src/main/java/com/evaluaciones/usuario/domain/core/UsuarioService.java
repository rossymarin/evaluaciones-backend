package com.evaluaciones.usuario.domain.core;

import com.evaluaciones.rol.domain.outgoing.RolRepository;
import com.evaluaciones.usuario.domain.Usuario;
import com.evaluaciones.usuario.domain.incoming.UsuarioLogic;
import com.evaluaciones.usuario.domain.incoming.exception.UserAlreadyExistsException;
import com.evaluaciones.usuario.domain.incoming.exception.UserNotFoundException;
import com.evaluaciones.usuario.domain.outgoing.UsuarioRepository;
import com.evaluaciones.usuarioRol.domain.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService implements UsuarioLogic {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario saveUser(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioDto = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioDto != null) {
            throw new UserAlreadyExistsException("El usuario ya esta registrado");
        } else {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioDto = usuarioRepository.save(usuario);
        }
        return usuarioDto;
    }

    @Override
    public Usuario getUser(String username) throws Exception {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            return usuario;
        } else {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Boolean existe = usuarioRepository.existsById(id);
        if (existe) {
            usuarioRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }
}
