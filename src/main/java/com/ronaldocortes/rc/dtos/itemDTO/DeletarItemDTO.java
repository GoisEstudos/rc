package com.ronaldocortes.rc.dtos.itemDTO;

import com.ronaldocortes.rc.entities.Item;

import java.math.BigDecimal;

public record DeletarItemDTO(Long id, String nome, String message) {
    public DeletarItemDTO(Item item) {
        this(
                item.getId(),
                item.getNomeItem(),
                "Item Deletado Com sucesso!"
        );
    }
}
