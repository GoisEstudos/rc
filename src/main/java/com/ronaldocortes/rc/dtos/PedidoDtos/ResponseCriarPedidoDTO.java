package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.dtos.PedidoItemDtos.ResponsePedidoItemDTO;
import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.entities.PedidoItem;
import com.ronaldocortes.rc.enuns.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseCriarPedidoDTO(
        Long id,
        StatusPedido status,
        Cliente cliente,
        LocalDateTime data,
        List<ResponsePedidoItemDTO> pedidoItems
) {

    public ResponseCriarPedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getCliente(),
                pedido.getData(),
                pedido.getItens()
                        .stream()
                        .map(ResponsePedidoItemDTO::new)
                        .toList()
        );
    }
}
