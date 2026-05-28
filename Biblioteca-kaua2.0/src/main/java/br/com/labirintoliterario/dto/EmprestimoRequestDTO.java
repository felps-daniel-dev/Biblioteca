package br.com.labirintoliterario.dto;

import br.com.labirintoliterario.entity.Cliente;
import br.com.labirintoliterario.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoRequestDTO {

    private Long cliente;
    private Long livro;


}
