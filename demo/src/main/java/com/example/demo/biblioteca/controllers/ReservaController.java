package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Reserva;
import com.example.demo.biblioteca.models.Usuario;
import com.example.demo.biblioteca.repositories.LivroRepository;
import com.example.demo.biblioteca.repositories.ReservaRepository;
import com.example.demo.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/poo/reserva")
public class ReservaController extends BaseController<Reserva, ReservaRepository> {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void atualizarPropriedades(Reserva dbEntidade, Reserva novaEntidade) {
        dbEntidade.setDataReserva(novaEntidade.getDataReserva());
        dbEntidade.setLivro(novaEntidade.getLivro());
        dbEntidade.setUsuario(novaEntidade.getUsuario());
        dbEntidade.setDataReserva(novaEntidade.getDataReserva());
    }

    public ResponseEntity<Reserva> getPorIdLivroIdUsuario(Long idLivro, Long idUsuario) {
        try {
            Optional<Livro> optLivro = livroRepository.findById(idLivro);
            Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
            Optional<Reserva> optReserva = getRepository().findByLivroAndUsuario(optLivro.get(), optUsuario.get());
            return new ResponseEntity<Reserva>(optReserva.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Reserva>(HttpStatus.NO_CONTENT);
        }
    }
}
