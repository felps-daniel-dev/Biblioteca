package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.mapper.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // vai retornar na lista todos de acordo com o status de entrada
    List<Emprestimo> findByStatus(StatusEmprestimo status);
}
