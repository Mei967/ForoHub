package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosRegistroRespuesta;
import com.alura.forohub.dto.DatosRespuestaRespuesta;
import com.alura.forohub.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrar(@RequestBody DatosRegistroRespuesta datos) {
        var respuesta = service.registrar(datos);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaRespuesta>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
