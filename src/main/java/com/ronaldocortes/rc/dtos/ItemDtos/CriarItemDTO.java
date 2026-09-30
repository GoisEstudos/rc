package com.ronaldocortes.rc.dtos.ItemDtos;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CriarItemDTO(
        Long id,
        StatusItem status,
        @NotBlank(message = "Nome Do item Não pode ser null ou em Branco")
        String nomeItem,
        BigDecimal preco
) {
    public CriarItemDTO(Item item) {
        this(
                item.getId(),
                item.getStatus(),
                item.getNomeItem(),
                item.getPreco()
        );
    }
}
