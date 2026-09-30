package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import com.ronaldocortes.rc.entities.Movimentacao;
import com.ronaldocortes.rc.enuns.OrigemMovimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BuscarMovimentacaoPorIdDTO(Long id, BigDecimal valor, LocalDateTime data, String descricao, TipoMovimentacao tipo, OrigemMovimentacao origem, Long pedidoId) {
    public BuscarMovimentacaoPorIdDTO(Movimentacao movimentacao) {
        this (
                movimentacao.getId(),
                movimentacao.getValor(),
                movimentacao.getData(),
                movimentacao.getDescricao(),
                movimentacao.getTipo(),
                movimentacao.getOrigem(),
                movimentacao.getPedido() != null ? movimentacao.getPedido().getId() : null
        );
    }
}
