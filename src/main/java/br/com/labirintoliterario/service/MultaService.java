package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.MultaResponseDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.entity.Multa;
import br.com.labirintoliterario.mapper.MultaMapper;
import br.com.labirintoliterario.mapper.StatusMulta;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import br.com.labirintoliterario.repository.MultaRepositrory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MultaService {

    @Autowired
    private MultaRepositrory multaRepository;

    @Autowired
    private MultaMapper mapper;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Multa  novaMulta(Long emprestimo_id){

        Emprestimo emprestimo = emprestimoRepository.findById(emprestimo_id)
                 .orElseThrow(()-> new IllegalArgumentException("Emprestimo não encontrado!"));

        Multa multa = new Multa();

        multa.setEmprestimo(emprestimo);
        multa.setStatus(StatusMulta.NAO_PAGA);

        multaRepository.save(multa);

        return multa;
    }

    public Multa buscarMultaPorEmprestimo(Long id_emprestimo){
        Multa multa = multaRepository.findByEmprestimoId(id_emprestimo)
                .orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));
        return multa;
    }


    public BigDecimal calculaMulta(Multa multa) {

        BigDecimal valorDiario = multa.getValorDiario();
        BigDecimal diasAtraso = BigDecimal.valueOf(multa.getDiasAtraso());

        BigDecimal valorTotal = valorDiario.multiply(diasAtraso);
        multa.setValorTotal(valorTotal);

        multaRepository.save(multa);

        return valorTotal;
    }
}
