package com.example.demo.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController<Entidade, Repositorio extends JpaRepository<Entidade, Long>> {
    @Autowired
    private Repositorio repository;

    public Repositorio getRepository() {
        return repository;
    }

    public void setRepository(Repositorio repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Entidade>> getAll() {
        return new ResponseEntity<List<Entidade>>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Entidade> getPorId(@PathVariable long codigo) {
        Optional<Entidade> optLivro = repository.findById(codigo);

        if(optLivro.isPresent()) {
            return new ResponseEntity<Entidade>(optLivro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Entidade> post(@RequestBody Entidade entidade) {
        Entidade savedEntidade = repository.save(entidade);
        return new ResponseEntity<Entidade>(savedEntidade, HttpStatus.OK);
    }

    @PutMapping("{codigo}")
    public ResponseEntity<Entidade> put(@PathVariable long codigo, @RequestBody Entidade entidade) {
        Optional<Entidade> optEntidade = repository.findById(codigo);
        if (optEntidade.isPresent()) {
            Entidade dbEntidade = optEntidade.get();
            //Atualizar os atributos
            atualizarPropriedades(dbEntidade, entidade);
            return new ResponseEntity<Entidade>(repository.save(dbEntidade), HttpStatus.OK);
        }
        
        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<Entidade> putPorString(Entidade paraAtualizar, Optional<Entidade> optEntidade) {
        if (optEntidade.isPresent()) {
            Entidade dbEntidade = optEntidade.get();
            //Atualizar os atributos
            atualizarPropriedades(dbEntidade, paraAtualizar);
            return new ResponseEntity<Entidade>(repository.save(dbEntidade), HttpStatus.OK);
        }
        
        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    protected abstract void atualizarPropriedades(Entidade dbEntidade, Entidade novaEntidade);
}
