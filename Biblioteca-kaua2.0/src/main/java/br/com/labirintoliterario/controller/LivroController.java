package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.dto.LivroRequestDto;
import br.com.labirintoliterario.dto.LivroResponseDto;
import br.com.labirintoliterario.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> adicionar(@RequestBody LivroRequestDto dto) {
        return ResponseEntity.ok(livroService.adicionar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroResponseDto>> listarDisponiveis() {
        return ResponseEntity.ok(livroService.listarDisponiveis());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizar(@PathVariable Long id, @RequestBody LivroRequestDto dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
