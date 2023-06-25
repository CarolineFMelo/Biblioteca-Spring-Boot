package com.example.demo.biblioteca.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Emprestimo extends BaseEntidade {
    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fk_biblioteca")
    private Biblioteca biblioteca;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
}
