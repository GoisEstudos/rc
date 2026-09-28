package com.ronaldocortes.rc.dtos.itemDtos;

import com.ronaldocortes.rc.entities.Item;

import java.math.BigDecimal;

public record AtualizarItemDTO(String nomeItem, BigDecimal preco) {
    public AtualizarItemDTO(Item item) {
        this(
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
