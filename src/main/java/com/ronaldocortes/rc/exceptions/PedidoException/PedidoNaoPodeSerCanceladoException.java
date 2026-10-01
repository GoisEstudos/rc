package com.ronaldocortes.rc.exceptions.PedidoException;

public class PedidoNaoPodeSerCanceladoException extends RuntimeException {
    public PedidoNaoPodeSerCanceladoException() {
        super("O pedido não pode ser cancelado porque não está fechado.");
    }
}
