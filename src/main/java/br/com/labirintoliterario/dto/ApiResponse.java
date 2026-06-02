package br.com.labirintoliterario.dto;

public record ApiResponse<T>(
        String mensagem,
        String status,
        T data
) {




}
