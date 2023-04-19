package com.evaluaciones.usuario.domain.incoming.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super("El usuario ya se encuentra registrado");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
