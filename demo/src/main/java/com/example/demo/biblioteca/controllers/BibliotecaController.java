package com.example.demo.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biblioteca.models.Biblioteca;
import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Reserva;
import com.example.demo.biblioteca.repositories.*;

@RestController
@RequestMapping("/poo/biblioteca")
public class BibliotecaController extends BaseController<Biblioteca, BibliotecaRepository> {

    @Autowired
    private EmprestimoController emprestimoController;

    @Autowired
    private LivroController livroController;

    @Autowired
    private ReservaController reservaController;

    @Override
    protected void atualizarPropriedades(Biblioteca dbEntidade, Biblioteca novaEntidade) {
        dbEntidade.setNome(novaEntidade.getNome());
    }

    // Métodos para livros
    @GetMapping("/livro")
    public ResponseEntity<List<Livro>> getAllLivros() {
        return livroController.getAll();
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<Livro> getPorIdLivro(@PathVariable Long id) {
        return livroController.getPorId(id);
    }

    @GetMapping("/livro/titulo/{titulo}")
    public ResponseEntity<Livro> getPorTituloLivro(@PathVariable String titulo) {
        return livroController.getPorTitulo(titulo);
    }

    @PostMapping("/livro")
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        return livroController.post(livro);
    }

    @DeleteMapping("/livro/{id}") 
    public ResponseEntity<Livro> deletePorIdLivro(@PathVariable Long id) {
        return livroController.deletePorID(id);
    }

    @DeleteMapping("/livro/titulo/{titulo}") 
    public ResponseEntity<Livro> deletePorTituloLivro(@PathVariable String titulo) {
        return livroController.deletePorTitulo(titulo);
    }

    // Métodos para Reservas
    @GetMapping("/reserva")
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return reservaController.getAll();
    }

    @PostMapping("/reserva")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        return reservaController.post(reserva);
    }

    @DeleteMapping("/reserva/{id}")
    public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id) {
        return reservaController.deletePorID(id);
    }

    @GetMapping("/reserva/porIdLivroIdUsuario/{idLivro}/{idUsuario}")
    public ResponseEntity<Reserva> getReservaPorIdLivroIdUsuario(@PathVariable Long idLivro, @PathVariable Long idUsuario) {
        return reservaController.getPorIdLivroIdUsuario(idLivro, idUsuario);
    }

    // Métodos para Empréstimos
    @GetMapping("/emprestimo")
    public ResponseEntity<List<Emprestimo>> getAllEmprestimos() {
        return emprestimoController.getAll();
    }

    @PostMapping("/emprestimo")
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoController.post(emprestimo);
    }

    @DeleteMapping("/emprestimo/{id}")
    public ResponseEntity<Emprestimo> deleteEmprestimo(@PathVariable Long id) {
        return emprestimoController.deletePorID(id);
    }

    @GetMapping("/emprestimo/porIdLivroIdUsuario/{idLivro}/{idUsuario}")
    public ResponseEntity<Emprestimo> getEmprestimoPorIdLivroIdUsuario(@PathVariable Long idLivro, @PathVariable Long idUsuario) {
        return emprestimoController.getPorIdLivroIdUsuario(idLivro, idUsuario);
    }

}
