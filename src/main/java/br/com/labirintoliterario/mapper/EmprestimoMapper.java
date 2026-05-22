package br.com.labirintoliterario.mapper;


import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {


     // vai pegar apenas o id do livro e cliente semppedir o objeto inteiro
    @Mapping(target = "livro", ignore = true)
    @Mapping(target = "cliente", ignore = true)

    //Vai converter o emprestimo request em entidade
    Emprestimo toEntity(EmprestimoRequestDTO request);

    @Mapping(target = "livroId", source = "livro.id")
    @Mapping(target = "clienteId", source = "cliente.id")
    // converte a entidade em response pra mostra apenas os campos do response
    EmprestimoRespsonseDTO toResponse(Emprestimo emprestimo);

    List<EmprestimoRespsonseDTO> toResponeList(List<Emprestimo> emprestimos);

}
