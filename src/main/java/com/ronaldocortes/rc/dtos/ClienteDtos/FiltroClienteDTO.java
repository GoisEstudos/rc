package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.enuns.StatusFiltroCliente;

public record FiltroClienteDTO(StatusFiltroCliente status, String nome, String telefone) {

}
