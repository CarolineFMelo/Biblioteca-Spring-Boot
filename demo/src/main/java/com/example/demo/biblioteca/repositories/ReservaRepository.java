package com.example.demo.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.biblioteca.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
}
