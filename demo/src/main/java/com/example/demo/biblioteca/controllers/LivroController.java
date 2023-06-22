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

import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.repositories.LivroRepository;

@RestController
@RequestMapping("/poo/livro")
public class LivroController extends BaseController<Livro, LivroRepository> {

    @Override
    protected void atualizarPropriedades(Livro dbEntidade, Livro novaEntidade) {
        dbEntidade.setAnoPublicacao(novaEntidade.getAnoPublicacao());
        dbEntidade.setAutor(novaEntidade.getAutor());
        dbEntidade.setDisponivel(novaEntidade.getDisponivel());
        dbEntidade.setTitulo(novaEntidade.getTitulo());
    }

    // ======================================
    // GET BY TITULO
    // ======================================

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> getPorTitulo(@PathVariable String titulo) {
        try {
            Livro optLivro = findByTitulo(titulo);
            return new ResponseEntity<Livro>(optLivro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Livro>(HttpStatus.NO_CONTENT);
        }

    }

    // ======================================
    // PUT BY TITULO
    // ======================================

    @PutMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> putPorTitulo(@PathVariable String titulo, @RequestBody Livro livro) {
        try {
            return putPorString(livro, findByTitulo(titulo));
        } catch (Exception e) {
            return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
        }
    }

    // ======================================
    // DELETE BY TITULO
    // ======================================

    @DeleteMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> deletePorTitulo(@PathVariable String titulo) {
        Livro livro = findByTitulo(titulo);
        getRepository().delete(livro);
        return new ResponseEntity<Livro>(HttpStatus.ACCEPTED);
    }

    private Livro findByTitulo(String titulo) {
        return getRepository().findByTitulo(titulo).orElseThrow(() -> new RuntimeException("Book not found - "));
    }

}
