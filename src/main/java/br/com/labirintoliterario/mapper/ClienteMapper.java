package br.com.labirintoliterario.mapper;

import br.com.labirintoliterario.dto.ClienteRequestDTO;
import br.com.labirintoliterario.dto.ClienteResponseDTO;
import br.com.labirintoliterario.entity.Cliente;
import org.springframework.lang.NonNull;

public class ClienteMapper {

    @NonNull
    public static Cliente toEntity(@NonNull ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        return cliente;
    }

    @NonNull
    public static ClienteResponseDTO toDTO(@NonNull Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setEndereco(cliente.getEndereco());

        return dto;
    }
}