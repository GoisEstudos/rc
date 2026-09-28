package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.enuns.StatusPedido;

import java.time.LocalDateTime;

public record FiltroPedidoDTO(StatusPedido status, LocalDateTime dataInicio, LocalDateTime dataFim, Long clienteId, Long itemId) {

}
