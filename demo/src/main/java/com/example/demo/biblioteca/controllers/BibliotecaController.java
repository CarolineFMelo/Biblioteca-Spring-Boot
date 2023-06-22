package com.example.demo.biblioteca.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Biblioteca;
import com.example.demo.biblioteca.repositories.*;
@RestController
@RequestMapping("/poo/biblioteca")
public class BibliotecaController extends BaseController<Biblioteca, BibliotecaRepository>{

    // @Autowired
    // private EmprestimoRepository emprestimoRepository;

    // @Autowired
    // private LivroRepository livroRepository;

    // @Autowired
    // private ReservaRepository reservaRepository;


    @Override
    protected void atualizarPropriedades(Biblioteca dbEntidade, Biblioteca novaEntidade) {
        dbEntidade.setNome(novaEntidade.getNome());
    }
    
}
