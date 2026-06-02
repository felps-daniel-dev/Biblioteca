package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.maper.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Retorna todos os empréstimos com o status informado
    List<Emprestimo> findByStatus(StatusEmprestimo status);

    // Verifica se existe empréstimo para um cliente
    boolean existsByCliente_Id(Long clienteId);
}