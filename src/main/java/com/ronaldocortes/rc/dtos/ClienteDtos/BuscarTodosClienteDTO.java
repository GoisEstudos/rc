package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;

public record BuscarTodosClienteDTO(Long id, StatusCliente statusCliente, String nome, String telefone) {
    public BuscarTodosClienteDTO(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getStatus(),
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
