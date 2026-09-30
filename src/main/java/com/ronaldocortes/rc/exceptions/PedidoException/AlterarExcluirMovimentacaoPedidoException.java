package com.ronaldocortes.rc.exceptions.PedidoException;

public class AlterarExcluirMovimentacaoPedidoException extends RuntimeException {
    public AlterarExcluirMovimentacaoPedidoException() {
        super("Movimentações originadas de pedidos não podem ser alteradas ou excluídas.");
    }
}
