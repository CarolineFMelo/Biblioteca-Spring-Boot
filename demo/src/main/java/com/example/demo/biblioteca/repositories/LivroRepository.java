package com.example.demo.biblioteca.repositories;

import java.util.Optional;


import com.example.demo.biblioteca.models.Livro;

public interface LivroRepository extends BaseRepository<Livro, Long> {

    public Optional<Livro> findByTitulo(String titulo);

    public void deleteByTitulo(String titulo);
    
}
