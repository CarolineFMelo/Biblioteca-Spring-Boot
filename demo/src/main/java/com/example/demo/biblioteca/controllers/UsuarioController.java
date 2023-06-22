package com.example.demo.biblioteca.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Usuario;
import com.example.demo.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/poo/usuario")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository> {

    @Override
    protected void atualizarPropriedades(Usuario dbEntidade, Usuario novaEntidade) {
        dbEntidade.setEmail(novaEntidade.getEmail());
        dbEntidade.setNome(novaEntidade.getNome());
        dbEntidade.setRa(novaEntidade.getRa());
    }

    // ======================================
    // GET BY EMAIL
    // ======================================

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getPorEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<Usuario>(findByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // GET BY RA
    // ======================================

    @GetMapping("/ra/{ra}")
    public ResponseEntity<Usuario> getPorRa(@PathVariable String ra) {
        try {
            return new ResponseEntity<Usuario>(findByRa(ra), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // PUT BY EMAIL
    // ======================================

    @PutMapping("/email/{email}")
    public ResponseEntity<Usuario> putPorEmail(@PathVariable String email, @RequestBody Usuario usuario) {
        try {
            return putPorString(usuario, findByEmail(email));
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // PUT BY RA
    // ======================================

    @PutMapping("/ra/{ra}")
    public ResponseEntity<Usuario> putPorRA(@PathVariable String ra, @RequestBody Usuario usuario) {
        try {
            return putPorString(usuario, findByRa(ra));
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // DELETE BY EMAIL
    // ======================================

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Usuario> deletePorEmail(@PathVariable String email) {
        try {
            getRepository().delete(findByEmail(email));
            return new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // DELETE BY RA
    // ======================================

    @DeleteMapping("/ra/{ra}")
    public ResponseEntity<Usuario> deletePorRa(@PathVariable String ra) {
        try {
            getRepository().delete(findByRa(ra));
            return new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
    }

    private Usuario findByEmail(String email) {
        return getRepository().findByEmail(email).orElseThrow(() -> new RuntimeException("User not found - "));
    }

    private Usuario findByRa(String ra) {
        return getRepository().findByRa(ra).orElseThrow(() -> new RuntimeException("User not found - "));
    }
}
