package com.ronaldocortes.rc.dtos.itemDTO;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;

import java.math.BigDecimal;

public record BuscarItemPorIdDTO(StatusItem status, String nomeItem, BigDecimal preco) {
    public BuscarItemPorIdDTO(Item item) {
        this (
                item.getStatus(),
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
