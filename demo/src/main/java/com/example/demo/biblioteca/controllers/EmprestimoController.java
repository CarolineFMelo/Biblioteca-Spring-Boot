package com.example.demo.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Usuario;
import com.example.demo.biblioteca.repositories.EmprestimoRepository;
import com.example.demo.biblioteca.repositories.LivroRepository;
import com.example.demo.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/poo/emprestimo")
public class EmprestimoController extends BaseController<Emprestimo, EmprestimoRepository> {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void atualizarPropriedades(Emprestimo dbEntidade, Emprestimo novaEntidade) {
        dbEntidade.setLivro(novaEntidade.getLivro());
        dbEntidade.setUsuario(novaEntidade.getUsuario());
        dbEntidade.setDataEmprestimo(novaEntidade.getDataEmprestimo());
    }

    public ResponseEntity<Emprestimo> getPorIdLivroIdUsuario(Long idLivro, Long idUsuario) {
        try {
            Optional<Livro> optLivro = livroRepository.findById(idLivro);
            Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
            if (!(optLivro.isPresent() && optUsuario.isPresent()))
                return new ResponseEntity<Emprestimo>(HttpStatus.BAD_REQUEST);
            Optional<Emprestimo> optEmprestimo = getRepository().findByLivroAndUsuario(optLivro.get(),
                    optUsuario.get());
            return new ResponseEntity<Emprestimo>(optEmprestimo.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Emprestimo>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Emprestimo> getPorTituloLivroRaUsuario(String titulo, String ra) {
        try {
            Optional<Livro> optLivro = livroRepository.findByTitulo(titulo);
            Optional<Usuario> optUsuario = usuarioRepository.findByRa(ra);
            if (!(optLivro.isPresent() && optUsuario.isPresent()))
                return new ResponseEntity<Emprestimo>(HttpStatus.BAD_REQUEST);
            Optional<Emprestimo> optEmprestimo = getRepository().findByLivroAndUsuario(optLivro.get(),
                    optUsuario.get());
            return new ResponseEntity<Emprestimo>(optEmprestimo.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Emprestimo>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Emprestimo> getPorTituloLivroEmailUsuario(String titulo, String email) {
        try {
            Optional<Livro> optLivro = livroRepository.findByTitulo(titulo);
            Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
            if (!(optLivro.isPresent() && optUsuario.isPresent()))
                return new ResponseEntity<Emprestimo>(HttpStatus.BAD_REQUEST);
            Optional<Emprestimo> optEmprestimo = getRepository().findByLivroAndUsuario(optLivro.get(),
                    optUsuario.get());
            return new ResponseEntity<Emprestimo>(optEmprestimo.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Emprestimo>(HttpStatus.NO_CONTENT);
        }
    }

}
