package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
