package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import com.ronaldocortes.rc.enuns.OrigemMovimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FiltroMovimentacaoDTO(Long id, BigDecimal valorInical, BigDecimal valorFim, LocalDateTime dataInicio, LocalDateTime dataFim, String descricao, TipoMovimentacao tipo, OrigemMovimentacao origem, Long pedidoId) {
}
