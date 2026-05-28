package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.dto.MultaResponseDTO;
import br.com.labirintoliterario.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    @Autowired
    private MultaService service;

    @GetMapping("/emprestimo/{id}")
    public ResponseEntity<MultaResponseDTO> buscarMultaPorEmprestimo(@PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarMultaPorEmprestimo(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaResponseDTO> buscarMultaPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarMultaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MultaResponseDTO>> listarMultas(){
        return ResponseEntity.ok(service.listarMultas());
    }
}
