package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.ClienteRequestDTO;
import br.com.labirintoliterario.dto.ClienteResponseDTO;
import br.com.labirintoliterario.entity.Cliente;
import br.com.labirintoliterario.mapper.ClienteMapper;
import br.com.labirintoliterario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);

        Cliente salvo = repository.save(cliente);

        return ClienteMapper.toDTO(salvo);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return ClienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        Cliente atualizado = repository.save(cliente);

        return ClienteMapper.toDTO(atualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}