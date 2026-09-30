package com.ronaldocortes.rc.dtos.ClienteDtos;

import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;
import org.hibernate.validator.constraints.Length;

public record CriarClienteDTO(Long id, StatusCliente statusCliente, String nome, @Length(min = 11, max = 11, message = "O numero Deve ter 11 Digitos, apenas numeros sem pontuação e letras") String telefone) {
    public CriarClienteDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getStatus(),
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
