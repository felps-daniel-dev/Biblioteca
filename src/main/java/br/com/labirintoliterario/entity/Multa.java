package br.com.labirintoliterario.entity;

import br.com.labirintoliterario.mapper.StatusMulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Multa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorTotal;// valor total a pagar

    private Long diasAtraso;

    //a multa vai ser cauculada apenas para o dia de atraso e não contara jutos ate o dia do pagamento
    private BigDecimal valorDiario;// valor de cada dia atrasado

    @Enumerated(EnumType.STRING)
    private StatusMulta status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    private LocalDateTime dataPagamento;

}

