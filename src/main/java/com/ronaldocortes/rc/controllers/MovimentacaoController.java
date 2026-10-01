package com.ronaldocortes.rc.controllers;

import com.ronaldocortes.rc.dtos.MovimentacaoDtos.*;
import com.ronaldocortes.rc.services.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    public ResponseEntity<BuscarMovimentacoesResponseDTO> buscarTodasMovimentacoes(@ModelAttribute FiltroMovimentacaoDTO filtroMovimentacao) {
        return ResponseEntity.status(200).body(movimentacaoService.buscarTodasMovimentacoes(filtroMovimentacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarMovimentacaoPorIdDTO> buscarMovimentacaoPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(movimentacaoService.buscarMovimentacaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<CriarMovimentacaoDTO> criarMovimentacao(@RequestBody CriarMovimentacaoDTO criarMovimentacao) {
        return ResponseEntity.status(201).body(movimentacaoService.criarMovimentacao(criarMovimentacao));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarMovimentacao(@PathVariable Long id) {
        movimentacaoService.cancelarMovimentacao(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AtualizarMoviementacaoDTO> atualizarMovimentacao(@RequestBody AtualizarMoviementacaoDTO atualizarMoviementacao, @PathVariable Long id) {
        return ResponseEntity.status(200).body(movimentacaoService.atualizarMoviementacao(atualizarMoviementacao, id));
    }
}
