package com.ronaldocortes.rc.exceptions.MovimentacaoException;

public class MovimentacaoNaoPodeSerEstornadaException extends RuntimeException {
    public MovimentacaoNaoPodeSerEstornadaException() {
        super("A movimentação não pode ser estornada porque não está finalizada.");
    }
}
