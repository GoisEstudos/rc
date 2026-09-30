package com.ronaldocortes.rc.dtos.ItemDtos;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;

import java.math.BigDecimal;

public record BuscarTodosItemDTO(Long id, StatusItem status, String nomeItem, BigDecimal preco) {

    public BuscarTodosItemDTO(Item item) {
        this(
                item.getId(),
                item.getStatus(),
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
