package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;

public record BuscarClientePorIdDTO(Long id, StatusCliente statusCliente, String nome, String telefone) {
    public BuscarClientePorIdDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getStatus(),
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
