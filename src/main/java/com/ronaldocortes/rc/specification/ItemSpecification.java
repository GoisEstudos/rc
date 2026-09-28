package com.ronaldocortes.rc.specification;

import com.ronaldocortes.rc.dtos.itemDtos.FiltroItemDTO;
import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ItemSpecification {

    public static Specification<Item> comFiltro(FiltroItemDTO filtro) {
        return statusContem(filtro.status())
                .and(nomeContem(filtro.nomeItem()))
                .and(precoContem(filtro.precoMinimo(), filtro.precoMaximo()));
    }

    private static Specification<Item> statusContem(
            StatusItem status) {

        return (root, query, cb) -> {

            if (status == null) {
                return cb.equal(
                        root.get("status"),
                        StatusItem.ATIVO
                );
            }

            if (status == StatusItem.TODOS) {
                return null;
            }

            return cb.equal(root.get("status"), status);
        };
    }

    private static Specification<Item> nomeContem(String nome) {

        return (root, query, cb) -> {

            if (nome == null || nome.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("nome")),
                    "%" + nome.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Item> precoContem(BigDecimal precoMinimo, BigDecimal precoMaximo) {
        return (root, query, cb) -> {

            if (precoMinimo == null && precoMaximo == null) {
                return null;
            }

            if (precoMinimo != null && precoMaximo != null) {
                return cb.between(
                        root.get("preco"),
                        precoMinimo,
                        precoMaximo
                );
            }

            if (precoMinimo != null) {
                return cb.greaterThanOrEqualTo(
                        root.get("preco"),
                        precoMinimo
                );
            }

            return cb.lessThanOrEqualTo(
                    root.get("preco"),
                    precoMaximo
            );
        };
    }
}
