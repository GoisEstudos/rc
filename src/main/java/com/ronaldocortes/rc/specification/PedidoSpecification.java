package com.ronaldocortes.rc.specification;

import com.ronaldocortes.rc.dtos.PedidoDtos.FiltroPedidoDTO;
import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.entities.PedidoItem;
import com.ronaldocortes.rc.enuns.StatusPedido;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class PedidoSpecification {

    public static Specification<Pedido> comFiltro(FiltroPedidoDTO filtro) {
        return statusContem(filtro.status())
                .and(clienteContem(filtro.clienteId()))
                .and(itemContem(filtro.itemId()))
                .and(dataEntre(filtro.dataInicio(), filtro.dataFim()));
    }

    private static Specification<Pedido> statusContem(
            StatusPedido status) {

        return (root, query, cb) -> {

            if (status == null) {
                return cb.equal(
                        root.get("status"),
                        StatusPedido.ABERTO
                );
            }

            if (status == StatusPedido.TODOS) {
                return null;
            }

            return cb.equal(root.get("status"), status);
        };
    }
    private static Specification<Pedido> clienteContem(Long clienteId) {

        return (root, query, cb) -> {

            if (clienteId == null) {
                return null;
            }

            return cb.equal(
                    root.get("cliente").get("id"),
                    clienteId
            );
        };
    }
    private static Specification<Pedido> itemContem(Long itemId) {

        return (root, query, cb) -> {

            if (itemId == null) {
                return null;
            }

            Join<Pedido, PedidoItem> pedidoItem =
                    root.join("itens");

            return cb.equal(
                    pedidoItem.get("item").get("id"),
                    itemId
            );
        };
    }

    private static Specification<Pedido> dataEntre(
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {

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
}
