package br.com.labirintoliterario.mapper;
import org.springframework.stereotype.Component;
import br.com.labirintoliterario.dto.LivroRquestDto;
import br.com.labirintoliterario.dto.LivroResponseDto;
import br.com.labirintoliterario.entity.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRquestDto dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setQuantidade(dto.getQuantidade());
        livro.setCategoria(dto.getCategoria());
        return livro;
    }

    public LivroResponseDto toDTO(Livro livro) {
        LivroResponseDto dto = new LivroResponseDto();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setIsbn(livro.getIsbn());
        dto.setQuantidade(livro.getQuantidade());
        dto.setCategoria(livro.getCategoria());
        return dto;
    }
}