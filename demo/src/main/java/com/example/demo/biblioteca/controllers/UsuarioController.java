package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Usuario;
import com.example.demo.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/poo/usuario/")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository> {

    @Override
    protected void atualizarPropriedades(Usuario dbEntidade, Usuario novaEntidade) {
        dbEntidade.setEmail(novaEntidade.getEmail());
        dbEntidade.setNome(novaEntidade.getNome());
        dbEntidade.setRa(novaEntidade.getRa());
    }

    @Override
    protected void deletePorString(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePorString'");
    }

    // ======================================
    // GET BY EMAIL
    // ======================================

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getPorEmail(@PathVariable String email) {
        Optional<Usuario> optUsuario = getRepository().findByEmail(email);

        if (optUsuario.isPresent()) {
            return new ResponseEntity<Usuario>(optUsuario.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }

    // ======================================
    // GET BY RA
    // ======================================

    @GetMapping("/ra/{ra}")
    public ResponseEntity<Usuario> getPorRa(@PathVariable String ra) {
        Optional<Usuario> optUsuario = getRepository().findByRa(ra);

        if (optUsuario.isPresent()) {
            return new ResponseEntity<Usuario>(optUsuario.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }

    // ======================================
    // PUT BY RA
    // ======================================

    @PutMapping("/ra/{ra}")
    public ResponseEntity<Usuario> putPorRA(@PathVariable String ra, @RequestBody Usuario usuario){
        Optional<Usuario> dbUsuario = getRepository().findByRa(ra);
        return putPorString(usuario, dbUsuario);
    }

    // ======================================
    // PUT BY EMAIL
    // ======================================

    @PutMapping("/email/{email}")
    public ResponseEntity<Usuario> putPorEmail(@PathVariable String email, @RequestBody Usuario usuario){
        Optional<Usuario> dbUsuario = getRepository().findByEmail(email);
        return putPorString(usuario, dbUsuario);
    }

}
