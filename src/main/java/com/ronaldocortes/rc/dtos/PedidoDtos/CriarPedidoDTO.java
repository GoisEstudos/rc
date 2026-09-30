package com.ronaldocortes.rc.dtos.PedidoDtos;

import com.ronaldocortes.rc.dtos.PedidoItemDtos.CriarPedidoItemDTO;

import java.util.List;

public record CriarPedidoDTO(Long clienteId, List<CriarPedidoItemDTO> itens) {
}
