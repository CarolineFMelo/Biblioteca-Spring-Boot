package com.example.demo.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
    
}
