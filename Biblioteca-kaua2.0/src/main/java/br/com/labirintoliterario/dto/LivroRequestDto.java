package br.com.labirintoliterario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDto {
    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidade;
    private String categoria;
}

//ja esta funcionando
// é preticamente igal a esse, só vai adicionar o id ali junto ble e vai trocar o nome
// ai hora que terminar eu te mostro o como faz o commit