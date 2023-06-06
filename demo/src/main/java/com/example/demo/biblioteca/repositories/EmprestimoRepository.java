package com.example.demo.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
}
