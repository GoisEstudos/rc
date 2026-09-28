package com.ronaldocortes.rc.dtos.PedidoItemDtos;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.entities.PedidoItem;

import java.math.BigDecimal;

public record ResponsePedidoItemDTO(
        Long id,
        Item item,
        BigDecimal valorMilheiro,
        Integer quantidade,
        BigDecimal valorTotal
) {

    public ResponsePedidoItemDTO(PedidoItem pedidoItem) {
        this(
                pedidoItem.getId(),
                pedidoItem.getItem(),
                pedidoItem.getValorMilheiro(),
                pedidoItem.getQuantidade(),
                pedidoItem.getValorTotal()
        );
    }
}
