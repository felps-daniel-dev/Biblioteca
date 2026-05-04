package br.com.labirintoliterario.repository;

import br.com.labirintoliterario.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
