package com.example.demo.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findByTitulo(String titulo);
    
}
