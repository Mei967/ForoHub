package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public void registrar (@RequestBody DatosRegistroTopico datos){
        Topico topico = new Topico(datos.titulo(), datos.mensaje(), datos.autor(),
                datos.curso());
        repository.save(topico);
    }
}
