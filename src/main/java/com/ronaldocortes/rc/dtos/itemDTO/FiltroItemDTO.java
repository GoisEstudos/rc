package com.ronaldocortes.rc.dtos.itemDTO;

import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;

import java.math.BigDecimal;

public record FiltroItemDTO(StatusItem status, String nomeItem, BigDecimal precoMinimo, BigDecimal precoMaximo) {
}
