package br.com.labirintoliterario.entity;

import br.com.labirintoliterario.mapper.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dataEmprestimo;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dataVencimento;// Data prevista
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dataDevolucao; // Fica null até o livro ser entregue

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
}



