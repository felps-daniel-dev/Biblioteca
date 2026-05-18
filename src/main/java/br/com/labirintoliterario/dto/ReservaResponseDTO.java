package br.com.labirintoliterario.dto;


import br.com.labirintoliterario.mapper.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private Long id;
    private Long cleiente;
    private Long livro;
    private LocalDateTime dataReserva;
    private StatusReserva status;
}
