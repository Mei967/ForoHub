package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosActualizarTopico;
import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.dto.DatosRespuestaTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.service.TopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/topico")

public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
    var dtoRespuesta = service.registrar(datos);
    URI uri = service.construirUri(dtoRespuesta.id(), uriBuilder);
    return ResponseEntity.created(uri).body(dtoRespuesta);
    }

    @GetMapping
    public List<DatosRespuestaTopico> listar() {
        return service.listar();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(@RequestBody DatosActualizarTopico datos) {
        DatosRespuestaTopico dtoRespuesta = service.actualizar(datos);
        return ResponseEntity.ok(dtoRespuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> consultarTopicoPorId(@PathVariable Long id) {
        var dto = service.consultarTopicoPorId(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
