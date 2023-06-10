package com.example.demo.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Usuario;
import com.example.demo.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario/")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository> {

    @Override
    protected void atualizarPropriedades(Usuario dbEntidade, Usuario novaEntidade) {
        dbEntidade.setEmail(novaEntidade.getEmail());
        dbEntidade.setNome(novaEntidade.getNome());
        dbEntidade.setRa(novaEntidade.getRa());
    }

}
