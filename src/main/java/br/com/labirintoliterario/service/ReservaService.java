package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Livro;
import br.com.labirintoliterario.entity.Reserva;
import br.com.labirintoliterario.mapper.StatusReserva;
import br.com.labirintoliterario.repository.LivroRepository;
import br.com.labirintoliterario.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    public ReservaResponseDTO salvar(ReservaRequestDTO dto) {

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getStatus().equals(StatusReserva.EMPRESTADO)) {
            throw new RuntimeException("Livro já emprestado");
        }

        Reserva reserva = new Reserva();

        reserva.setCliente(dto.getCliente());
        reserva.setLivro(livro);

        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.RESERVADO);

        Reserva reservaSalva = repository.save(reserva);

        return new ReservaResponseDTO(
                reservaSalva.getId(),
                reservaSalva.getCliente().getId(),
                reservaSalva.getLivro().getId(),
                reservaSalva.getDataReserva(),
                reservaSalva.getStatus()
        );
    }

    public List<ReservaResponseDTO> listarTodos() {

        List<Reserva> reservas = repository.findAll();

        return reservas.stream()
                .map(reserva -> new ReservaResponseDTO(
                        reserva.getId(),
                        reserva.getCliente().getId(),
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