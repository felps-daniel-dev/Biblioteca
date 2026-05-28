package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
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
import java.util.Optional;

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
    public EmprestimoRespsonseDTO salvar(EmprestimoRequestDTO dto){

        Emprestimo emprestimo = mapper.toEntity(dto);

        emprestimo.setDataEmprestimo(LocalDateTime.now());

        Livro livro = livroRepository.findById(dto.getLivro())
                .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado!"));

        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado!"));

        emprestimo.setLivro(livro);
        emprestimo.setCliente(cliente);

        // tratamento pra ver se tem alguma quantidade disponivel
        if(livro.getQuantidade() <= 0){
            throw new IllegalArgumentException("Este livro não tem nenhuma unidade disponivel!");
        }
        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.save(livro);


        emprestimo.setDataVencimento(LocalDateTime.now().plusDays(10));
        emprestimo.setStatus(StatusEmprestimo.ANDAMENTO);

        emprestimo = emprestimoRepository.save(emprestimo);

        return mapper.toResponse(emprestimo);
    }

    @Transactional
    public void remover(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Emprestimo não encontrado"));

        if(emprestimo.getStatus().equals(StatusEmprestimo.ANDAMENTO)){
            throw new IllegalArgumentException("Você não pode excluir um empréstimo que está em andamento");
        } else if (emprestimo.getStatus().equals(StatusEmprestimo.CONCLUIDO)) {
            throw new IllegalArgumentException("O emprestimo já foi concluido!");
        }
        // vai fazer a alteração e salvar no banco
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimoRepository.save(emprestimo);
        //emprestimoRepository.delete(emprestimo);
    }

    public List<EmprestimoRespsonseDTO> listarTodos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return mapper.toResponeList(emprestimos);
    }

    public List<EmprestimoRespsonseDTO> listarPorStatus(StatusEmprestimo status) {
        List<Emprestimo> emprestimoAndamento = emprestimoRepository.findByStatus(status);
        return mapper.toResponeList(emprestimoAndamento);
    }

    @Transactional
    public EmprestimoRespsonseDTO realizarDevolucao(Long id) {

        BigDecimal valorMulta = new BigDecimal("2.00"); // 2 reais por dia

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                 .orElseThrow(()-> new IllegalArgumentException("Emprestimo não encontrado"));

        if (emprestimo.getStatus().equals(StatusEmprestimo.CONCLUIDO)){
            throw new IllegalArgumentException("O emprestimo já foi encerrado!");
        }

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId())
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        livro.setQuantidade(livro.getQuantidade() + 1);
        livroRepository.save(livro);


        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimo.setDataDevolucao(LocalDateTime.now());

        emprestimoRepository.save(emprestimo);

        // ccauculo da multa
        if(emprestimo.getDataDevolucao().isAfter(emprestimo.getDataVencimento())){
            //calculo de dias
            Long diasDeAtraso = ChronoUnit.DAYS.between(emprestimo.getDataVencimento(), emprestimo.getDataDevolucao());

            Multa multa = new Multa();

            multa.setDiasAtraso(diasDeAtraso);
            multa.setValorDiario(valorMulta);// multa fixa de dois reais
            multa.setEmprestimo(emprestimo);

            multaService.novaMulta(multa);
        }

        return mapper.toResponse(emprestimo);
    }
}
