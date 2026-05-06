package br.com.labirintoliterario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Livros")
@Data
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


