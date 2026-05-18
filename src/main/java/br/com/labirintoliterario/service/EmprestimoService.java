package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.mapper.EmprestimoMapper;
import br.com.labirintoliterario.mapper.StatusEmprestimo;
import br.com.labirintoliterario.repository.ClienteRepository;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import br.com.labirintoliterario.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoMapper mapper;

    public EmprestimoRespsonseDTO salvar(EmprestimoRequestDTO dto){

        Emprestimo emprestimo = mapper.toEntity(dto);

        emprestimo.setDataEmprestimo(LocalDateTime.now());

        livroRepository.findById(emprestimo.getLivro().getId())
                .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado!"));

        clienteRepository.findById(emprestimo.getCliente().getId())
                .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado!"));

        emprestimo.setDataVencimento(LocalDateTime.now().plusDays(10));
        emprestimo.setStatus(StatusEmprestimo.ANDAMENTO);

        emprestimo = repository.save(emprestimo);

        return mapper.toResponse(emprestimo);
    }

    public void remover(Long id){
        Emprestimo emprestimo = repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Emprestimo não encontrado"));

        if(emprestimo.getStatus().equals(StatusEmprestimo.ANDAMENTO)){
            throw new IllegalArgumentException("Você não pode excluir um empréstimo que está em andamento");
        }
        // vai fazer a alteração e salvar no banco
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        repository.save(emprestimo);
        //repository.delete(emprestimo);
    }

    public List<EmprestimoRespsonseDTO> listarTodos(){
        List<Emprestimo> emprestimos = repository.findAll();
        return mapper.toResponeList(emprestimos);
    }

    public List<EmprestimoRespsonseDTO> listarPorStatus(StatusEmprestimo status) {
        List<Emprestimo> emprestimoAndamento = repository.findByStatus(status);
        return mapper.toResponeList(emprestimoAndamento);
    }

    public EmprestimoRespsonseDTO realizarDevolucao(Long id) {
        Emprestimo emprestimo = repository.findById(id)
                 .orElseThrow(()-> new IllegalArgumentException("Emprestimo não encontrado"));

        if (emprestimo.getStatus().equals(StatusEmprestimo.CONCLUIDO)){
            throw new IllegalArgumentException("O emprestimo já foi encerrado!");
        }
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimo.setDataDevolucao(LocalDateTime.now());
        // altera as multas
        repository.save(emprestimo);

        return mapper.toResponse(emprestimo);
    }
}
