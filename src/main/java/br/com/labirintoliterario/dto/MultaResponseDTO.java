package br.com.labirintoliterario.dto;


import br.com.labirintoliterario.maper.StatusMulta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultaResponseDTO {

    private Long id;
    private BigDecimal valorTotal;
    private Integer diasAtraso;
    private StatusMulta status;
    private Long emprestimo_id;
    private LocalDateTime dataPagamento;
}
