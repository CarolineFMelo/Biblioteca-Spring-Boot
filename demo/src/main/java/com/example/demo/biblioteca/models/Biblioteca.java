package com.example.demo.biblioteca.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Biblioteca extends BaseEntidade{

    private String nome;

    @JsonManagedReference
    @OneToMany(mappedBy = "biblioteca")
    private List<Livro> livros;

    @JsonManagedReference
    @OneToMany(mappedBy = "biblioteca")
    private List<Reserva> reservas;

    @JsonManagedReference
    @OneToMany(mappedBy = "biblioteca")
    private List<Emprestimo> emprestimos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}
