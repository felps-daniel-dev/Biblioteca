package br.com.labirintoliterario.controller;
import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Reserva;
import br.com.labirintoliterario.maper.StatusReserva;
import br.com.labirintoliterario.repository.ReservaRepository;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RestController

public class ReservaController {

    @Autowired
    private ReservaRepository repository;

    public ReservaResponseDTO salvar(ReservaRequestDTO dto) {

        Reserva reserva = new Reserva();

        reserva.setCliente(dto.clienteId());
        reserva.setLivro(dto.livroId());

        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.DISPONIVEL);

        Reserva reservaSalva = repository.save(reserva);

        return new ReservaResponseDTO(
                reservaSalva.getId(),
                reservaSalva.getClienteId(),
                reservaSalva.getLivro(),
                reservaSalva.getDataReserva(),
                reservaSalva.getStatus()
        );
    }
@GetMapping

    public List<ReservaResponseDTO> listarTodos() {

        List<Reserva> reservas = repository.findAll();

        return reservas.stream()
                .map(reserva -> new ReservaResponseDTO(
                        reserva.getId(),
                        reserva.getClienteId(),
                        reserva.getLivro().getId(),
                        reserva.getDataReserva(),
                        reserva.getStatus()
                ))
                .toList();
    }

    public void remover(Long id) {

        repository.deleteById(id);
    }
}

