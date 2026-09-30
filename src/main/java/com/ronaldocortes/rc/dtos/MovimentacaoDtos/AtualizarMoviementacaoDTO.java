package com.ronaldocortes.rc.dtos.MovimentacaoDtos;

import com.ronaldocortes.rc.entities.Movimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AtualizarMoviementacaoDTO(BigDecimal valor, LocalDateTime data, String descricao, TipoMovimentacao tipo) {
    public AtualizarMoviementacaoDTO(Movimentacao movimentacao) {
        this(
                movimentacao.getValor(),
                movimentacao.getData(),
                movimentacao.getDescricao(),
                movimentacao.getTipo()
        );
    }
}
