 package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.dto.ReservaRequestDTO;
import br.com.labirintoliterario.dto.ReservaResponseDTO;
import br.com.labirintoliterario.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> salvar(@RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(reservaService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        reservaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}