package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import java.math.BigDecimal;

public record ResumoMovimentacoesDTO(
        BigDecimal valorTotalEntrada,
        BigDecimal valorTotalSaida,
        BigDecimal saldo
) {
}
