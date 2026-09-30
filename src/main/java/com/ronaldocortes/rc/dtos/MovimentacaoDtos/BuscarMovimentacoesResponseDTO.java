package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import java.util.List;

public record BuscarMovimentacoesResponseDTO(
        List<BuscarTodasMovimentacoesDTO> movimentacoes,
        ResumoMovimentacoesDTO resumo
) {
}
