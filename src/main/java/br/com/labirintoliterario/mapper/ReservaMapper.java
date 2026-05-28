package br.com.labirintoliterario.mapper;

import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservaMapper {

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "livro", ignore = true)
    Reserva toEntity(ReservaRequestDTO request);

    @Mapping(source = "cliente.id", target = "clienteid")
    @Mapping(source = "livro.id", target = "livro")
    ReservaResponseDTO toResponse(Reserva reserva);

    List<ReservaResponseDTO> toResponseList(List<Reserva> reservas);
}