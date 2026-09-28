package com.ronaldocortes.rc.dtos.itemDtos;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;

import java.math.BigDecimal;

public record BuscarTodosItemDTO(StatusItem status, String nomeItem, BigDecimal preco) {

    public BuscarTodosItemDTO(Item item) {
        this(
                item.getStatus(),
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
