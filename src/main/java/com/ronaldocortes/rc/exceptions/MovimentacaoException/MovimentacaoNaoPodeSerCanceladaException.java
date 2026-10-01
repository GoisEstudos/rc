package com.ronaldocortes.rc.exceptions.MovimentacaoException;

public class MovimentacaoNaoPodeSerCanceladaException extends RuntimeException {
    public MovimentacaoNaoPodeSerCanceladaException() {
        super("A movimentação não pode ser cancelada porque não está finalizada.");
    }
}
