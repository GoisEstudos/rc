package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;

public record DeletarClienteDTO(Long id, String nome, String message) {
    public DeletarClienteDTO(Cliente cliente) {
        this (
                cliente.getId(),
                cliente.getNome(),
                "Cliente Deletado Com sucesso!"
        );
    }
}
