package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.enuns.StatusPedido;

public record FecharPedidoDTO(Long id, StatusPedido status) {
    public FecharPedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus()
        );
    }
}
