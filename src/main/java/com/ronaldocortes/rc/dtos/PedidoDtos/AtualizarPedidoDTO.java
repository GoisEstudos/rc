package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.enuns.StatusPedido;

public record AtualizarPedidoDTO(Long id, StatusPedido status) {
    public AtualizarPedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus()
        );
    }
}
