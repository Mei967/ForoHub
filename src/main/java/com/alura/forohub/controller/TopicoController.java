package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.dto.DatosRespuestaTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/topico")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        Topico topico = new Topico(datos);
        repository.save(topico);

        URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaTopico (topico));

    }
}
