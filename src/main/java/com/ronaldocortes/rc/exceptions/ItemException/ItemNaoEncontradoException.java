package com.ronaldocortes.rc.exceptions.ItemException;

import com.ronaldocortes.rc.exceptions.RecursoNaoEncontradoException;

public class ItemNaoEncontradoException extends RecursoNaoEncontradoException {
    public ItemNaoEncontradoException(Long id) {
        super("Item não encontrado com o ID: " + id);
    }
}
