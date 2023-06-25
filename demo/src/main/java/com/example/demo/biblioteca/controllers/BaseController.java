package com.example.demo.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.biblioteca.models.BaseEntidade;
import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Reserva;
import com.example.demo.biblioteca.repositories.BaseRepository;
import com.example.demo.biblioteca.repositories.EmprestimoRepository;
import com.example.demo.biblioteca.repositories.LivroRepository;
import com.example.demo.biblioteca.repositories.ReservaRepository;

public abstract class BaseController<Entidade extends BaseEntidade, Repositorio extends BaseRepository<Entidade, Long>> {
    @Autowired
    private Repositorio repository;

    @Autowired
    private LivroRepository livroRepositorio;

    @Autowired
    private ReservaRepository reservaRepository;

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
        try {
            return new ResponseEntity<List<Entidade>>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Entidade>>(HttpStatus.BAD_REQUEST);
        }
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
        try {
            if (entidade instanceof Emprestimo) {
                Emprestimo entEmprestimo = (Emprestimo) entidade;
                Livro livro = entEmprestimo.getLivro();
                Optional<Livro> optLivro = livroRepositorio.findById(livro.getId());
                boolean disponivel = optLivro.get().getDisponivel();

                if (!disponivel)
                    return new ResponseEntity<Entidade>(HttpStatus.BAD_REQUEST);

                Optional<Reserva> optReserva = reservaRepository.findByLivroAndUsuario(livro,
                        entEmprestimo.getUsuario());
                if (optReserva.isPresent()) {
                    reservaRepository.deleteById(optReserva.get().getId());
                }

                optLivro.get().setDisponivel(false);
                livroRepositorio.save(optLivro.get());
            } else if (entidade instanceof Reserva) {
                Reserva entReserva = (Reserva) entidade;
                Livro livro = entReserva.getLivro();
                Optional<Livro> optLivro = livroRepositorio.findById(livro.getId());
                boolean disponivel = optLivro.get().getDisponivel();
                if (disponivel)
                    return new ResponseEntity<Entidade>(HttpStatus.BAD_REQUEST);
            }

            Entidade savedEntidade = repository.save(entidade);
            repository.refresh(savedEntidade);
            return new ResponseEntity<Entidade>(savedEntidade, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.BAD_REQUEST);
        }
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
            Optional<Entidade> entidade = repository.findById(id);
            if (entidade.get() instanceof Emprestimo) {
                Emprestimo entEmprestimo = (Emprestimo) entidade.get();
                Livro livro = entEmprestimo.getLivro();
                Optional<Livro> optLivro = livroRepositorio.findById(livro.getId());
                optLivro.get().setDisponivel(true);
                livroRepositorio.save(optLivro.get());
            }
            repository.deleteById(id);
            return new ResponseEntity<Entidade>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Entidade>(HttpStatus.BAD_REQUEST);
        }
    }

    protected abstract void atualizarPropriedades(Entidade dbEntidade, Entidade novaEntidade);

    private Entidade findById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found - "));
    }
}
