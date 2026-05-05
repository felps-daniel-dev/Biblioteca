package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepositrory extends JpaRepository<Multa, Long> {
}
