package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.repositories.LivroRepository;

@RestController
@RequestMapping("/api")
public class LivroController {
    
    @Autowired
    private LivroRepository repository;

    @GetMapping("poo/livro/{codigo}")
    public ResponseEntity<Livro> getLivroPorId(@PathVariable long codigo) {
        Optional<Livro> optLivro = repository.findById(codigo);

        if(optLivro.isPresent()) {
            return new ResponseEntity<Livro>(optLivro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Livro>(HttpStatus.NO_CONTENT);
    }

}
