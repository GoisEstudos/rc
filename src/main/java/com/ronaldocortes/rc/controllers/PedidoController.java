package com.ronaldocortes.rc.controllers;

import com.ronaldocortes.rc.dtos.PedidoDtos.*;
import com.ronaldocortes.rc.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<BuscarTodosPedidosDTO>> buscarTodosProdutos(@ModelAttribute FiltroPedidoDTO filtroPedidoDTO) {
        return ResponseEntity.status(200).body(pedidoService.buscarTodosItem(filtroPedidoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarPedidoPorIdDTO> buscarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(pedidoService.buscarPedidoPorId(id));
    }

    @PatchMapping("/{id}/fechar")
    public ResponseEntity<FecharPedidoDTO> fecharPedido(@PathVariable Long id) {
        return ResponseEntity.status(200).body(pedidoService.fecharPedido(id));
    }

    @PostMapping
    public ResponseEntity<ResponseCriarPedidoDTO> criarPedido(@RequestBody CriarPedidoDTO criarPedidoDTO) {
        return ResponseEntity.status(201).body(pedidoService.criarPedido(criarPedidoDTO));
    }

    @DeleteMapping("/{id}/cancelar")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        ResponseEntity.noContent().build();
    }
}
