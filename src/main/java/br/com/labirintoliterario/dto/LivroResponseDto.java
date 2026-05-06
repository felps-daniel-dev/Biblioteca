package br.com.labirintoliterario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto{
    private long id ;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidade;
    private String categoria;
}
