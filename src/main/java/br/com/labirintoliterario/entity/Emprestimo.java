package br.com.labirintoliterario.entity;

import br.com.labirintoliterario.maper.StatusEmprestimo;
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

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "livro_id")
    //private Livro livro;

    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataVencimento;// Data prevista
    private LocalDateTime dataDevolucao;  // Fica null até o livro ser entregue

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
}



