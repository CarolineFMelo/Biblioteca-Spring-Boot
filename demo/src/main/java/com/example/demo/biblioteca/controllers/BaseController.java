package com.example.demo.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.biblioteca.models.BaseEntidade;

public abstract class BaseController<Entidade extends BaseEntidade, Repositorio extends JpaRepository<Entidade, Long>> {
    @Autowired
    private Repositorio repository;

    public Repositorio getRepository() {
        return repository;
    }

    public void setRepository(Repositorio repository) {
        this.repository = repository;
    }

    // ======================================
    // GET ALL
    // ======================================

    @GetMapping
    public ResponseEntity<List<Entidade>> getAll() {
        return new ResponseEntity<List<Entidade>>(repository.findAll(), HttpStatus.OK);
    }

    // ======================================
    // GET BY ID
    // ======================================

    @GetMapping("{codigo}")
    public ResponseEntity<Entidade> getPorId(@PathVariable long codigo) {
        Optional<Entidade> optLivro = repository.findById(codigo);

        if (optLivro.isPresent()) {
            return new ResponseEntity<Entidade>(optLivro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    // ======================================
    // POST CREATE
    // ======================================

    @PostMapping
    public ResponseEntity<Entidade> post(@RequestBody Entidade entidade) {
        Entidade savedEntidade = repository.save(entidade);
        Optional<Entidade> dbEntidade = repository.findById(savedEntidade.getId());
        return new ResponseEntity<Entidade>(dbEntidade.get(), HttpStatus.OK);
    }

    // ======================================
    // PUT BY ID
    // ======================================

    @PutMapping("{codigo}")
    public ResponseEntity<Entidade> put(@PathVariable long codigo, @RequestBody Entidade entidade) {
        Optional<Entidade> optEntidade = repository.findById(codigo);
        if (optEntidade.isPresent()) {
            Entidade dbEntidade = optEntidade.get();
            atualizarPropriedades(dbEntidade, entidade);
            return new ResponseEntity<Entidade>(repository.save(dbEntidade), HttpStatus.OK);
        }
        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    // ======================================
    // PUT BY STRING
    // ======================================

    protected ResponseEntity<Entidade> putPorString(Entidade paraAtualizar, Optional<Entidade> optEntidade) {
        if (optEntidade.isPresent()) {
            return putPorString(paraAtualizar, optEntidade.get());
        }
        return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<Entidade> putPorString(Entidade paraAtualizar, Entidade dbEntidade) {
        atualizarPropriedades(dbEntidade, paraAtualizar);
        return new ResponseEntity<Entidade>(repository.save(dbEntidade), HttpStatus.OK);
    }

    // ======================================
    // DELETE BY ID
    // ======================================

    @DeleteMapping("{codigo}")
    public ResponseEntity<Entidade> deletePorID(@PathVariable long codigo) {
        try {
            repository.deleteById(codigo);
            return new ResponseEntity<Entidade>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    protected abstract void atualizarPropriedades(Entidade dbEntidade, Entidade novaEntidade);
}
