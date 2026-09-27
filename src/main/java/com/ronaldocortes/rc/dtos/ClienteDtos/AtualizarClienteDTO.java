package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;

public record AtualizarClienteDTO(String nome, String telefone) {
    public AtualizarClienteDTO(Cliente cliente) {
        this(
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
