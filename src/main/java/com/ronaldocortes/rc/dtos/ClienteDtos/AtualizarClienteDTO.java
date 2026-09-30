package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;

public record AtualizarClienteDTO(Long id, String nome, String telefone) {
    public AtualizarClienteDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
