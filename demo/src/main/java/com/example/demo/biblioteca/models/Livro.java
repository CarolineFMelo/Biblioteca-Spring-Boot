package com.example.demo.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Livro extends BaseEntidade {

    @Column(unique = true)
    private String titulo;

    private String autor;

    private int anoPublicacao;

    private boolean disponivel;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "fk_biblioteca")
    private Biblioteca biblioteca;

    @OneToOne(mappedBy = "livro")
    private Emprestimo emprestimo;

    @OneToOne(mappedBy = "livro")
    private Reserva reserva;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
