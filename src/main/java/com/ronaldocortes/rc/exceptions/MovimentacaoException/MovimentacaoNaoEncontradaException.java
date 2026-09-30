package com.ronaldocortes.rc.exceptions.MovimentacaoException;

import com.ronaldocortes.rc.exceptions.RecursoNaoEncontradoException;

public class MovimentacaoNaoEncontradaException extends RecursoNaoEncontradoException {
    public MovimentacaoNaoEncontradaException(Long id) {
        super("Movimentação não encontrado com o ID: " + id);
    }
}
