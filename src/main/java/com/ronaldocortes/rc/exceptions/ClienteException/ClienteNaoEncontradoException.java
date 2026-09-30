package com.ronaldocortes.rc.exceptions.ClienteException;

import com.ronaldocortes.rc.exceptions.RecursoNaoEncontradoException;

public class ClienteNaoEncontradoException extends RecursoNaoEncontradoException {
    public ClienteNaoEncontradoException(Long id) {
        super("Cliente não encontrado com o ID: " + id);
    }
}
