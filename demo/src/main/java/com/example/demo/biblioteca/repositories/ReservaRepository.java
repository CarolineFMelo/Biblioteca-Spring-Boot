package com.example.demo.biblioteca.repositories;

import java.util.Optional;

import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Reserva;
import com.example.demo.biblioteca.models.Usuario;

public interface ReservaRepository extends BaseRepository<Reserva, Long> {

    Optional<Reserva> findByLivroAndUsuario(Livro livro, Usuario usuario);
    
}
