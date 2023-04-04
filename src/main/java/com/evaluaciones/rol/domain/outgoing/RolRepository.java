package com.evaluaciones.rol.domain.outgoing;

import com.evaluaciones.rol.domain.Rol;
import com.evaluaciones.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

}
