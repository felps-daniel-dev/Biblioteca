package br.com.labirintoliterario.mapper;

import br.com.labirintoliterario.dto.MultaResponseDTO;
import br.com.labirintoliterario.entity.Multa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    MultaResponseDTO toResponse(Multa multa);
}
