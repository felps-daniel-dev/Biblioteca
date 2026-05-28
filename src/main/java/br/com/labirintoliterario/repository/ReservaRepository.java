package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
