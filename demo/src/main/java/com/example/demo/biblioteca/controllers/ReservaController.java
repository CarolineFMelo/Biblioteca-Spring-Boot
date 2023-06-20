package com.example.demo.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Reserva;
import com.example.demo.biblioteca.repositories.ReservaRepository;

@RestController
@RequestMapping("/poo/reserva")
public class ReservaController extends BaseController<Reserva, ReservaRepository>{

    @Override
    protected void atualizarPropriedades(Reserva dbEntidade, Reserva novaEntidade) {
        dbEntidade.setDataReserva(novaEntidade.getDataReserva());
        dbEntidade.setLivro(novaEntidade.getLivro());
    }

    @Override
    protected void deletePorString(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePorString'");
    }
    
}
