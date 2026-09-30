package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.dtos.ClienteDtos.BuscarClientePorIdDTO;
import com.ronaldocortes.rc.dtos.PedidoItemDtos.ResponsePedidoItemDTO;
import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.enuns.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record BuscarPedidoPorIdDTO(Long id, StatusPedido status, BuscarClientePorIdDTO cliente, LocalDateTime data, List<ResponsePedidoItemDTO> pedidoItems) {
    public BuscarPedidoPorIdDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus(),
                new BuscarClientePorIdDTO(pedido.getCliente()),
                pedido.getData().withNano(0),
                pedido.getItens().stream().map(ResponsePedidoItemDTO::new).toList()
        );
    }
}
