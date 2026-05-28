package br.com.labirintoliterario.dto;

import br.com.labirintoliterario.mapper.StatusEmprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoRespsonseDTO {

    private Long id;
    private Long clienteId;
    private Long livroId;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataVencimento;
    private StatusEmprestimo status;

}
