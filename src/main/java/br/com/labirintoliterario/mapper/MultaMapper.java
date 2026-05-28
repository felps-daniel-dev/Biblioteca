package br.com.labirintoliterario.mapper;

import br.com.labirintoliterario.dto.MultaResponseDTO;
import br.com.labirintoliterario.entity.Multa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    @Mapping(target = "emprestimo_id", source = "emprestimo.id")
    MultaResponseDTO toResponse(Multa multa);

    List<MultaResponseDTO> toResponseList(List<Multa> multas);
}
