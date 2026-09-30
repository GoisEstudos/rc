package com.ronaldocortes.rc.dtos.errosDtos;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        int status,
        String mensagem,
        LocalDateTime data
) {
}
