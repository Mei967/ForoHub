package com.alura.forohub.infra.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GestionDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> manejarEntityNotFound(EntityNotFoundException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> manejarUsernameNotFound(UsernameNotFoundException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, "Usuario no encontrado.");
    }
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> manejarJwtInvalido(JWTVerificationException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, "Token inválido o expirado.");
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> manejarErroresGenericos(RuntimeException ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> manejarResponseStatus(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        return construirRespuesta(status, ex.getReason());
    }

    private ResponseEntity<Object> construirRespuesta(HttpStatus status, String mensaje) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("status", status.value());
        cuerpo.put("error", status.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return new ResponseEntity<>(cuerpo, status);
    }
}

