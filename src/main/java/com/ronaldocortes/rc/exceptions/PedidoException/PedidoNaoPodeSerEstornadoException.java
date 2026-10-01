package com.ronaldocortes.rc.exceptions.PedidoException;

public class PedidoNaoPodeSerEstornadoException extends RuntimeException {
    public PedidoNaoPodeSerEstornadoException() {
        super("O pedido não pode ser estornado porque não está fechado.");
    }
}
