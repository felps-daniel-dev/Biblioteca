package br.com.labirintoliterario.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;

    public void setEndereco(String endereco) {
    }

    public void setTelefone(String telefone) {
    }
}
