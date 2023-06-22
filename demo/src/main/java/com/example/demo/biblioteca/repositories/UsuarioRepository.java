package com.example.demo.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Optional<Usuario> findByEmail(String email);

   Optional<Usuario> findByRa(String ra);
}
