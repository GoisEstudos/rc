package com.ronaldocortes.rc.exceptions.PedidoException;

import com.ronaldocortes.rc.exceptions.RecursoNaoEncontradoException;

public class PedidoNaoEncontradoException extends RecursoNaoEncontradoException {
    public PedidoNaoEncontradoException(Long id) {
        super("Pedido não encontrado com o ID: " + id);
    }
}
