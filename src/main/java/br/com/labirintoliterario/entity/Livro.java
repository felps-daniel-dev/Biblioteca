package br.com.labirintoliterario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // identificador unico

    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidade;
    private String categoria;
}


