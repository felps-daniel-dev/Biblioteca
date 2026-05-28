package br.com.labirintoliterario.service;

import br.com.labirintoliterario.dto.LivroRequestDto;
import br.com.labirintoliterario.dto.LivroResponseDto;
import br.com.labirintoliterario.entity.Livro;
import br.com.labirintoliterario.mapper.LivroMapper;
import br.com.labirintoliterario.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public LivroResponseDto adicionar(LivroRequestDto dto) {
        Livro livro = livroMapper.toEntity(dto);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toDTO(salvo);
    }

    public LivroResponseDto buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livroMapper.toDTO(livro);
    }

    public List<LivroResponseDto> listarDisponiveis() {
        return livroRepository.findAll()
                .stream()
                .filter(livro -> livro.getQuantidade() > 0)
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDto atualizar(Long id, LivroRequestDto dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setQuantidade(dto.getQuantidade());
        livro.setCategoria(dto.getCategoria());
        Livro atualizado = livroRepository.save(livro);
        return livroMapper.toDTO(atualizado);
    }

    public void excluir(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livroRepository.delete(livro);
    }
}
