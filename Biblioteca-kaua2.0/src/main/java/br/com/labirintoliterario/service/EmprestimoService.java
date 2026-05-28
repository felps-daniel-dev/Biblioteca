package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.entity.Cliente;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.entity.Livro;
import br.com.labirintoliterario.entity.Multa;
import br.com.labirintoliterario.mapper.EmprestimoMapper;
import br.com.labirintoliterario.mapper.StatusEmprestimo;
import br.com.labirintoliterario.repository.ClienteRepository;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import br.com.labirintoliterario.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EmprestimoMapper mapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private MultaService multaService;

    @Transactional
    public EmprestimoRespsonseDTO salvar(EmprestimoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente nao encontrado!"));

        Livro livro = livroRepository.findById(dto.getLivro())
                .orElseThrow(() -> new IllegalArgumentException("Livro nao encontrado!"));

        if (livro.getQuantidade() == null || livro.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Livro indisponivel para emprestimo!");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setDataVencimento(LocalDateTime.now().plusDays(10));
        emprestimo.setStatus(StatusEmprestimo.ANDAMENTO);

        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.save(livro);

        emprestimo = emprestimoRepository.save(emprestimo);

        return mapper.toResponse(emprestimo);
    }

    @Transactional
    public void remover(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprestimo nao encontrado"));

        if (emprestimo.getStatus().equals(StatusEmprestimo.ANDAMENTO)) {
            throw new IllegalArgumentException("Voce nao pode excluir um emprestimo que esta em andamento");
        }

        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimoRepository.save(emprestimo);
    }

    public List<EmprestimoRespsonseDTO> listarTodos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return mapper.toResponeList(emprestimos);
    }

    public List<EmprestimoRespsonseDTO> listarPorStatus(StatusEmprestimo status) {
        List<Emprestimo> emprestimoAndamento = emprestimoRepository.findByStatus(status);
        return mapper.toResponeList(emprestimoAndamento);
    }

    @Transactional
    public EmprestimoRespsonseDTO realizarDevolucao(Long id) {
        BigDecimal valorMulta = new BigDecimal("2.00");

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprestimo nao encontrado"));

        if (emprestimo.getStatus().equals(StatusEmprestimo.CONCLUIDO)) {
            throw new IllegalArgumentException("O emprestimo ja foi encerrado!");
        }

        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimo.setDataDevolucao(LocalDateTime.now());

        Livro livro = emprestimo.getLivro();
        livro.setQuantidade(livro.getQuantidade() + 1);
        livroRepository.save(livro);

        if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataVencimento())) {
            Long diasDeAtraso = ChronoUnit.DAYS.between(
                    emprestimo.getDataVencimento(),
                    emprestimo.getDataDevolucao()
            );

            Multa multa = multaService.novaMulta(emprestimo.getId());
            multa.setDiasAtraso(diasDeAtraso);
            multa.setValorDiario(valorMulta);

            multaService.calculaMulta(multa);
        }

        emprestimoRepository.save(emprestimo);

        return mapper.toResponse(emprestimo);
    }
}
