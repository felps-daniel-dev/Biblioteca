package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Cliente;
import br.com.labirintoliterario.entity.Livro;
import br.com.labirintoliterario.entity.Reserva;
import br.com.labirintoliterario.mapper.StatusEmprestimo;
import br.com.labirintoliterario.mapper.StatusReserva;
import br.com.labirintoliterario.repository.ClienteRepository;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import br.com.labirintoliterario.repository.LivroRepository;
import br.com.labirintoliterario.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;
    private final EmprestimoRepository emprestimoRepository;

    public ReservaService(
            ReservaRepository repository,
            LivroRepository livroRepository,
            ClienteRepository clienteRepository,
            EmprestimoRepository emprestimoRepository
    ) {
        this.repository = repository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public ReservaResponseDTO salvar(ReservaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro nao encontrado"));

        if (repository.existsByLivroIdAndStatus(livro.getId(), StatusReserva.RESERVADO)) {
            throw new RuntimeException("Ja existe uma reserva para este livro");
        }

        boolean livroEmprestado = emprestimoRepository.findByStatus(StatusEmprestimo.ANDAMENTO)
                .stream()
                .anyMatch(emprestimo -> emprestimo.getLivro().getId().equals(livro.getId()))
                || emprestimoRepository.findByStatus(StatusEmprestimo.ATRASADO)
                .stream()
                .anyMatch(emprestimo -> emprestimo.getLivro().getId().equals(livro.getId()));

        if (livroEmprestado) {
            throw new RuntimeException("Livro ja foi emprestado");
        }

        if (livro.getQuantidade() == null || livro.getQuantidade() <= 0) {
            throw new RuntimeException("Livro indisponivel para reserva");
        }

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setLivro(livro);
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.RESERVADO);

        Reserva reservaSalva = repository.save(reserva);
        return toResponse(reservaSalva);
    }

    public List<ReservaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    private ReservaResponseDTO toResponse(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getCliente().getId(),
                reserva.getLivro().getId(),
                reserva.getDataReserva(),
                reserva.getStatus()
        );
    }
}