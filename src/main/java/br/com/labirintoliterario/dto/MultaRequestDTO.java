package br.com.labirintoliterario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultaRequestDTO {

    private Long emprestimo;
    private BigDecimal valorDiario;// valor de cada dia atrasado


}
