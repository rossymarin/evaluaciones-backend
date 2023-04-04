package com.evaluaciones.usuario.domain.application;

import com.evaluaciones.rol.domain.Rol;
import com.evaluaciones.usuario.domain.Usuario;
import com.evaluaciones.usuario.domain.incoming.UsuarioLogic;
import com.evaluaciones.usuarioRol.domain.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioLogic usuarioLogic;

    @PostMapping("/")
    public Usuario saveUser(@RequestBody Usuario usuario) throws Exception {
        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(1L);
        rol.setNombre("ADMIN");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        return usuarioLogic.saveUser(usuario, roles);
    }

    @GetMapping("/{username}")
    public Usuario getUser(@PathVariable("username") String username) throws Exception {
        return usuarioLogic.getUser(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void deleteUser(@PathVariable Long usuarioId) throws Exception {
        usuarioLogic.deleteUser(usuarioId);
    }
}
