package br.com.labirintoliterario.service;

import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.entity.Multa;
import br.com.labirintoliterario.mapper.MultaMapper;
import br.com.labirintoliterario.mapper.MultaMapperImpl;
import br.com.labirintoliterario.mapper.StatusMulta;
import br.com.labirintoliterario.repository.EmprestimoRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test") // isso obriga o Spring a ler o application-test.properties e ignorar o perfil local
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({MultaService.class, MultaMapperImpl.class})
class MultaServiceTest {

    @Autowired
    private MultaService multaService;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Test
    void consultaCalculoMulta() {
        Multa multa = new Multa();
        multa.setId(1L);
        multa.setDiasAtraso(10L);
        multa.setValorDiario(new BigDecimal("2.50"));

        multaService.calculaMulta(multa);

        //assertEquals(multa.getValorTotal(), new BigDecimal("25.00"));
        Assertions.assertAll("O valor esta de acordo com o esperado",
                // boa prática do JUnit esperado primeiro, atual depois
                () -> Assertions.assertEquals(new BigDecimal("25.00"), multa.getValorTotal()),
                () -> Assertions.assertEquals(10L, multa.getDiasAtraso())

        );
    }

    @Test
    void salvarNovaMultaComSucesso() {
        Emprestimo emprestimoTeste = new Emprestimo();

        Emprestimo emprestimo = emprestimoRepository.save(emprestimoTeste);

        Multa multaAtual = new Multa();
        multaAtual.setDiasAtraso(10L);
        multaAtual.setValorDiario(new BigDecimal("5.00"));
        multaAtual.setEmprestimo(emprestimo);

        Multa multa = multaService.novaMulta(multaAtual);




        multaService.novaMulta(multaAtual);

        Assertions.assertAll("Vlidação de persistencia e valores",
                // boa prática do JUnit esperado primeiro, atual depois
                () -> Assertions.assertNotNull(multa.getId(), "O Id nãao foi criado"),
                () -> Assertions.assertEquals(StatusMulta.NAO_PAGA, multa.getStatus(), "O status inicial"),
                () -> Assertions.assertEquals(new BigDecimal("50.00"), multa.getValorTotal(), "O Valor deveria ser 50.00")

        );
    }
}