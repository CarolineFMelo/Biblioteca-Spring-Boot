package com.example.demo.biblioteca.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.repositories.EmprestimoRepository;

@RestController
@RequestMapping("/poo/emprestimo")
public class EmprestimoController extends BaseController<Emprestimo, EmprestimoRepository> {

    @Override
    protected void atualizarPropriedades(Emprestimo dbEntidade, Emprestimo novaEntidade) {
        dbEntidade.setLivro(novaEntidade.getLivro());
        dbEntidade.setUsuario(novaEntidade.getUsuario());
        dbEntidade.setDataEmprestimo(novaEntidade.getDataEmprestimo());
    }
}
