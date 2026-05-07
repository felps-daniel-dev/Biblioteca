package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.maper.EmprestimoMapper;
import br.com.labirintoliterario.maper.StatusEmprestimo;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private EmprestimoMapper mapper;

    public EmprestimoRespsonseDTO salvar(EmprestimoRequestDTO dto){

        //Transforma o requestDTO em Entity usando o Mapper
        Emprestimo emprestimo = mapper.toEntity(dto);

        emprestimo.setDataEmprestimo(LocalDateTime.now());

        //Aqui eu deififni que o a data de vaencimento é depois de 7 dias depois do emprestimo
        emprestimo.setDataVencimento(LocalDateTime.now().plusDays(10));
        emprestimo.setStatus(StatusEmprestimo.ANDAMENTO);

        //dataDevolucao vai ser o dia que a pesso de fato devolver

        //salvendo no banco
        emprestimo = repository.save(emprestimo);

        //tranforma a entidade em response para devolver
        return mapper.toResponse(emprestimo);
    }
}
