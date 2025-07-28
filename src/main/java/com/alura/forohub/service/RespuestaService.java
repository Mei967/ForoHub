package com.alura.forohub.service;

import com.alura.forohub.dto.DatosRegistroRespuesta;
import com.alura.forohub.dto.DatosRespuestaRespuesta;
import com.alura.forohub.model.Respuesta;
import com.alura.forohub.repository.RespuestaRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaRespuesta registrar(DatosRegistroRespuesta datos) {
        var topico = topicoRepository.findById(datos.topicoId()).orElseThrow();
        var autor = usuarioRepository.findById(datos.autorId()).orElseThrow();

        var respuesta = new Respuesta(null, datos.mensaje(), LocalDateTime.now(), topico, autor);
        repository.save(respuesta);

        return new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                autor.getLogin(),
                topico.getId()
        );
    }
    public List<DatosRespuestaRespuesta> listar() {
        return repository.findAll().stream()
                .map(r -> new DatosRespuestaRespuesta(
                        r.getId(),
                        r.getMensaje(),
                        r.getFechaCreacion(),
                        r.getAutor().getLogin(),
                        r.getTopico().getId()
                ))
                .toList();
    }
}
