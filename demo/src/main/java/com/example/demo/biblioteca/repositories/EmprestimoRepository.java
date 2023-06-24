package com.example.demo.biblioteca.repositories;

import java.util.Optional;

import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Usuario;

public interface EmprestimoRepository extends BaseRepository<Emprestimo, Long> {

    Optional<Emprestimo> findByLivroAndUsuario(Livro livro, Usuario usuario);
    
}
