package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.MultaResponseDTO;
import br.com.labirintoliterario.entity.Multa;
import br.com.labirintoliterario.mapper.MultaMapper;
import br.com.labirintoliterario.mapper.StatusMulta;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import br.com.labirintoliterario.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private MultaMapper mapper;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Transactional
    public Multa  novaMulta(Multa multa){

        multa.setStatus(StatusMulta.NAO_PAGA);

        calculaMulta(multa);

        multa = multaRepository.save(multa);

        return multa;
    }

    public MultaResponseDTO buscarMultaPorEmprestimo(Long emprestimoId){
        Multa multa = multaRepository.findByEmprestimoId(emprestimoId)
                .orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));
        return mapper.toResponse(multa);
    }

    public MultaResponseDTO buscarMultaPorId(Long id){
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));
        return mapper.toResponse(multa);
    }

    @Transactional
    public Multa calculaMulta(Multa multa) {

        BigDecimal valorDiario = multa.getValorDiario();
        BigDecimal diasAtraso = BigDecimal.valueOf(multa.getDiasAtraso());

        BigDecimal valorTotal = valorDiario.multiply(diasAtraso);
        multa.setValorTotal(valorTotal);

        return multa;
    }

    public List<MultaResponseDTO> listarMultas() {
        return mapper.toResponseList(multaRepository.findAll());
    }
}
