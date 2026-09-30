package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import com.ronaldocortes.rc.entities.Movimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CriarMovimentacaoDTO(Long id, BigDecimal valor, String descricao, TipoMovimentacao tipo) {
    public CriarMovimentacaoDTO(Movimentacao  movimentacao) {
        this(
                movimentacao.getId(),
                movimentacao.getValor(),
                movimentacao.getDescricao(),
                movimentacao.getTipo()
        );
    }
}
