package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.dto.ClienteRequestDTO;
import br.com.labirintoliterario.dto.ClienteResponseDTO;
import br.com.labirintoliterario.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ClienteResponseDTO criar(@RequestBody ClienteRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}