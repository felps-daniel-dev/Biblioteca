package br.com.labirintoliterario.dto;

import java.math.BigDecimal;

public record LivroRquestDto<categoria>(

         String titulo,
         String autor,
         String isbn,
        Integer quantidade,
         String categoria) {
}
