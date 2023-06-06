package com.example.demo.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
