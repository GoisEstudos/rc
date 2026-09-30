package com.ronaldocortes.rc.specification;

import com.ronaldocortes.rc.dtos.MovimentacaoDtos.FiltroMovimentacaoDTO;
import com.ronaldocortes.rc.entities.Movimentacao;
import com.ronaldocortes.rc.enuns.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoSpecification {

    public static Specification<Movimentacao> comFiltro(FiltroMovimentacaoDTO filtro) {
        return valorEntre(filtro.valorInical(), filtro.valorFim())
                .and(dataEntre(filtro.dataInicio(), filtro.dataFim()))
                .and(descricaoContem(filtro.descricao()))
                .and(tipoMovimentacao(filtro.tipo()))
                .and(origemMovimentacao(filtro.origem()))
                .and(pedidoContem(filtro.pedidoId()));
    }

    private static Specification<Movimentacao> valorEntre(BigDecimal valorInical, BigDecimal valorFim) {
        return (root, query, cb) -> {

            if (valorInical != null && valorFim != null) {
                return cb.between(
                        root.get("valor"),
                        valorInical,
                        valorFim
                );
            }

            if (valorInical != null) {
                return cb.greaterThanOrEqualTo(
                        root.get("valor"),
                        valorInical
                );
            }

            if (valorFim != null) {
                return cb.lessThanOrEqualTo(
                        root.get("valor"),
                        valorFim
                );
            }

            return null;
        };
    }

    private static Specification<Movimentacao> dataEntre(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return (root, query, cb) -> {

            if (dataInicio != null && dataFim != null) {
                return cb.between(
                        root.get("data"),
                        dataInicio,
                        dataFim
                );
            }

            if (dataInicio != null) {
                return cb.greaterThanOrEqualTo(
                        root.get("data"),
                        dataInicio
                );
            }

            if (dataFim != null) {
                return cb.lessThanOrEqualTo(
                        root.get("data"),
                        dataFim
                );
            }

            return null;
        };
    }

    private static Specification<Movimentacao> descricaoContem(String descricao) {
        return (root, query, cb) -> {

            if (descricao == null || descricao.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("descricao")),
                    "%" + descricao.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Movimentacao> tipoMovimentacao(TipoMovimentacao tipo) {
        return (root, query, cb) -> {


            if (tipo == null) {
                return null;
            }

            return cb.equal(root.get("tipo"), tipo);
        };
    }

    private static Specification<Movimentacao> origemMovimentacao(OrigemMovimentacao origem) {
        return (root, query, cb) -> {

            if (origem == null) {
                return null;
            }

            return cb.equal(root.get("origem"), origem);
        };
    }

    private static Specification<Movimentacao> pedidoContem(Long pedidoId) {
        return (root, query, cb) -> {

            if (pedidoId == null) {
                return null;
            }

            return cb.equal(
                    root.get("pedido").get("id"),
                    pedidoId
            );
        };
    }

}
