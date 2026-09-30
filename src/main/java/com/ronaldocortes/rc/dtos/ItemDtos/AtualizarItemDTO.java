package com.ronaldocortes.rc.dtos.ItemDtos;

import com.ronaldocortes.rc.entities.Item;

import java.math.BigDecimal;

public record AtualizarItemDTO(Long id, String nomeItem, BigDecimal preco) {
    public AtualizarItemDTO(Item item) {
        this(
                item.getId(),
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
