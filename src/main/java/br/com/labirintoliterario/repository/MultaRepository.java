package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    Optional<Multa> findByEmprestimoId(Long id);
}
