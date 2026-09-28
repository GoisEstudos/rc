package com.ronaldocortes.rc.dtos.itemDtos;

import com.ronaldocortes.rc.entities.Item;

public record DeletarItemDTO(Long id, String nome, String message) {
    public DeletarItemDTO(Item item) {
        this(
                item.getId(),
                item.getNomeItem(),
                "Item Deletado Com sucesso!"
        );
    }
}
