package com.example.demo.biblioteca.repositories;


import com.example.demo.biblioteca.models.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
   Optional<Usuario> findByEmail(String email);

   Optional<Usuario> findByRa(String ra);
}
