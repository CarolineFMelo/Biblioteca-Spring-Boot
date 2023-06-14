package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Livro;
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

    @PutMapping("email/{email}")
    public ResponseEntity<Usuario> putPorTitulo(@PathVariable String email, @RequestBody Usuario usuario){
        Optional<Usuario> dbLivro = getRepository().findByEmail(email);
        return putPorString(usuario, dbLivro);
    }

}
