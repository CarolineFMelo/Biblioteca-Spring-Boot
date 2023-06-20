package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.repositories.LivroRepository;

@RestController
@RequestMapping("/poo/livro")
public class LivroController extends BaseController<Livro, LivroRepository>{

    @Override
    protected void atualizarPropriedades(Livro dbEntidade, Livro novaEntidade) {
        dbEntidade.setAnoPublicacao(novaEntidade.getAnoPublicacao());
        dbEntidade.setAutor(novaEntidade.getAutor());
        dbEntidade.setDisponivel(novaEntidade.getDisponivel());
        dbEntidade.setTitulo(novaEntidade.getTitulo());
    }

    @Override
    protected void deletePorString(String string) {
        getRepository().deleteByTitulo(string);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> getPorTitulo(@PathVariable String titulo) {
        Optional<Livro> optLivro = getRepository().findByTitulo(titulo);

        if(optLivro.isPresent()) {
            return new ResponseEntity<Livro>(optLivro.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Livro>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> putPorTitulo(@PathVariable String titulo, @RequestBody Livro livro){
        Optional<Livro> dbLivro = getRepository().findByTitulo(titulo);
        return putPorString(livro, dbLivro);
    }

    @DeleteMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> delteProTitulo(@PathVariable String titulo){
        return deletarPorString(titulo);
    }

}
