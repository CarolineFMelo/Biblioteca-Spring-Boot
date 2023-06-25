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

import com.example.demo.biblioteca.models.Emprestimo;
import com.example.demo.biblioteca.models.Livro;
import com.example.demo.biblioteca.models.Reserva;

@RestController
@RequestMapping("/poo/biblioteca")
public class BibliotecaController {

    @Autowired
    private EmprestimoController emprestimoController;

    @Autowired
    private LivroController livroController;

    @Autowired
    private ReservaController reservaController;

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
    public ResponseEntity<Reserva> getReservaPorIdLivroIdUsuario(@PathVariable Long idLivro,
            @PathVariable Long idUsuario) {
        return reservaController.getPorIdLivroIdUsuario(idLivro, idUsuario);
    }

    @GetMapping("/reserva/porNomeLivroRaUsuario/{titulo}/{raUsuario}")
    public ResponseEntity<Reserva> getReservaPorNomeLivroRaUsuario(@PathVariable String titulo,
            @PathVariable String raUsuario) {
        return reservaController.getPorTituloLivroRaUsuario(titulo, raUsuario);
    }

    @GetMapping("/reserva/porNomeLivroEmailUsuario/{tituloLivro}/{emailUsuario}")
    public ResponseEntity<Reserva> getReservaPorNomeLivroEmailUsuario(@PathVariable String tituloLivro,
            @PathVariable String emailUsuario) {
        return reservaController.getPorTituloLivroEmailUsuario(tituloLivro, emailUsuario);
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
    public ResponseEntity<Emprestimo> getEmprestimoPorIdLivroIdUsuario(@PathVariable Long idLivro,
            @PathVariable Long idUsuario) {
        return emprestimoController.getPorIdLivroIdUsuario(idLivro, idUsuario);
    }

    @GetMapping("/emprestimo/porNomeLivroRAUsuario/{titulo}/{ra}")
    public ResponseEntity<Emprestimo> getEmprestimoPorTituloLivroRaUsuario(@PathVariable String titulo,
            @PathVariable String ra) {
        return emprestimoController.getPorTituloLivroRaUsuario(titulo, ra);
    }

    @GetMapping("/emprestimo/porNomeLivroEmailUsuario/{titulo}/{email}")
    public ResponseEntity<Emprestimo> getEmprestimoPorTituloLivroEmailUsuario(@PathVariable String titulo,
            @PathVariable String email) {
        return emprestimoController.getPorTituloLivroEmailUsuario(titulo, email);
    }

}
