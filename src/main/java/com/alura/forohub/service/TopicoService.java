package com.alura.forohub.service;


import com.alura.forohub.dto.DatosActualizarTopico;
import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.dto.DatosRespuestaTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos) {
        Topico topico = new Topico(datos);
        repository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    public URI construirUri(Long id, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("/topico/{id}").buildAndExpand(id).toUri();
    }

    public List<DatosRespuestaTopico> listar() {
        return repository.findAll().stream()
                .map(DatosRespuestaTopico::new)
                .toList();
    }

    public DatosRespuestaTopico actualizar(DatosActualizarTopico datos) {
        Topico topico = repository.findById(datos.id())
                .orElseThrow(() -> new EntityNotFoundException("ID de tópico no encontrado"));

        topico.actualizarDatos(datos); // 👈 Se usa tu método aquí

        return new DatosRespuestaTopico(topico);
    }




    public void eliminar(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tópico con ID " + id + " no existe."));
        repository.delete(topico);
    }
}
