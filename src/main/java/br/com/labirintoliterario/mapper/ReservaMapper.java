package br.com.labirintoliterario.mapper;


import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import br.com.labirintoliterario.entity.Reserva;
import ch.qos.logback.core.model.ComponentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")

public interface ReservaMapper {


    @Mapping(target ="Cliente", ignore = true )
    @Mapping(target = "livro", ignore = true)

    Reserva Toentity(ReservaRequestDTO request);

    @Mapping( source = "Cliente.id", target = "Clienteid")

    ReservaResponseDTO Toresponse (Reserva reserva);

    List<ReservaResponseDTO> toResponeList(List<Reserva> reservas);




}
