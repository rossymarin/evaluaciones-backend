package com.evaluaciones.usuario.domain.application;

import com.evaluaciones.security.domain.domain.core.UserDetailsServiceImpl;
import com.evaluaciones.security.domain.incoming.model.JwtRequest;
import com.evaluaciones.security.domain.outgoing.model.JwtResponse;
import com.evaluaciones.security.infrastructure.config.JwtUtils;
import com.evaluaciones.usuario.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            String token = this.jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception exception){
            log.info(exception.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }
}
