package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.dtos.ClienteDtos.BuscarClientePorIdDTO;
import com.ronaldocortes.rc.dtos.PedidoItemDtos.ResponsePedidoItemDTO;
import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.enuns.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BuscarTodosPedidosDTO(Long id, StatusPedido status, LocalDateTime data, BuscarClientePorIdDTO cliente, List<ResponsePedidoItemDTO> pedidoItems, BigDecimal valorTotal) {
    public BuscarTodosPedidosDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getData().withNano(0),
                new BuscarClientePorIdDTO(pedido.getCliente()),
                pedido.getItens().stream().map(ResponsePedidoItemDTO::new).toList(),
                pedido.calcularValorTotal()
        );
    }
}
