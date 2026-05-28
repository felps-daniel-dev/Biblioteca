package br.com.labirintoliterario.mapper;


import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.entity.Reserva;
import ch.qos.logback.core.model.ComponentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ReservaMapper {


    @Mapping(target ="cliente", ignore = true )
    @Mapping(target = "livro", ignore = true)

    Reserva Toentity(ReservaRequestDTO request);

    @Mapping( source = "cliente.id", target = "clienteid")
    @Mapping( source = "livro.id" , target = "livro")
    ReservaResponseDTO Toresponse (Reserva reserva);

    List<ReservaResponseDTO> toResponeList(List<Reserva> reservas);




}
