package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Reserva;
import br.com.labirintoliterario.mapper.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByLivroIdAndStatus(Long livroId, StatusReserva status);
}