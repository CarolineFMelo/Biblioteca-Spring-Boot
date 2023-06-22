package com.example.demo.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.biblioteca.models.BaseEntidade;
import com.example.demo.biblioteca.repositories.BaseRepository;

public abstract class BaseController<Entidade extends BaseEntidade, Repositorio extends BaseRepository<Entidade, Long>> {
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

    @GetMapping("{id}")
    public ResponseEntity<Entidade> getPorId(@PathVariable long id) {
        try {
            return new ResponseEntity<Entidade>(findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
        }
    }

    // ======================================
    // POST CREATE
    // ======================================

    @PostMapping
    public ResponseEntity<Entidade> post(@RequestBody Entidade entidade) {
        Entidade savedEntidade = repository.save(entidade);
        repository.refresh(savedEntidade);
        return new ResponseEntity<Entidade>(savedEntidade, HttpStatus.OK);
    }

    // ======================================
    // PUT BY ID
    // ======================================

    @PutMapping("{id}")
    public ResponseEntity<Entidade> put(@PathVariable long id, @RequestBody Entidade entidade) {
        try {
            Entidade dbEntidade = findById(id);
            atualizarPropriedades(dbEntidade, entidade);
            repository.save(dbEntidade);
            repository.refresh(dbEntidade);
            return new ResponseEntity<Entidade>(dbEntidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
        }
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

    @DeleteMapping("{id}")
    public ResponseEntity<Entidade> deletePorID(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<Entidade>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected abstract void atualizarPropriedades(Entidade dbEntidade, Entidade novaEntidade);

    private Entidade findById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found - "));
    }
}
