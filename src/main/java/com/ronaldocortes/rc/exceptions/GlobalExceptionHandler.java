package com.ronaldocortes.rc.exceptions;

import com.ronaldocortes.rc.dtos.errosDtos.ErroResponseDTO;
import com.ronaldocortes.rc.exceptions.ClienteException.ClienteNaoEncontradoException;
import com.ronaldocortes.rc.exceptions.ItemException.ItemNaoEncontradoException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoEncontradaException;
import com.ronaldocortes.rc.exceptions.PedidoException.AlterarExcluirMovimentacaoPedidoException;
import com.ronaldocortes.rc.exceptions.PedidoException.PedidoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> recursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResponseDTO erro = new ErroResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AlterarExcluirMovimentacaoPedidoException.class)
    public ResponseEntity<ErroResponseDTO> alterarExcluirMovimentacaoPedido(AlterarExcluirMovimentacaoPedidoException ex) {
        ErroResponseDTO erro = new ErroResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}


