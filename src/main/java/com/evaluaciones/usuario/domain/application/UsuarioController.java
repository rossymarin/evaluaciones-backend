package com.evaluaciones.usuario.domain.application;

import com.evaluaciones.rol.domain.Rol;
import com.evaluaciones.usuario.domain.Usuario;
import com.evaluaciones.usuario.domain.incoming.UsuarioLogic;
import com.evaluaciones.usuarioRol.domain.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController  
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioLogic usuarioLogic;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioLogic.saveUser(usuario,usuarioRoles);
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
