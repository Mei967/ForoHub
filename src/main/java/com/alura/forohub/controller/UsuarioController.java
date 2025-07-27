package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosRegistroUsuario;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody DatosRegistroUsuario datos) {
        if (usuarioRepository.findByLogin(datos.login()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("El login ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario(datos, passwordEncoder);
        usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.ok().build();
    }

}

