package com.example.demo.biblioteca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    public Optional<Livro> findByTitulo(String titulo);

    public void deleteByTitulo(String titulo);
    
}
