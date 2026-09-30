package com.ronaldocortes.rc.controllers;

import com.ronaldocortes.rc.dtos.ClienteDtos.*;
import com.ronaldocortes.rc.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<BuscarTodosClienteDTO>> buscarClientes(@ModelAttribute FiltroClienteDTO filtroClienteDTO) {
        return ResponseEntity.status(200).body(clienteService.buscarClientes(filtroClienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarClientePorIdDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(clienteService.buscarClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<CriarClienteDTO> criarCliente(@Valid @RequestBody CriarClienteDTO criarClienteDTO) {
       return ResponseEntity.status(201).body(clienteService.criarCliente(criarClienteDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@RequestBody AtualizarClienteDTO atualizarClienteDTO, @PathVariable Long id) {
        return ResponseEntity.status(200).body(clienteService.atualizarCliente(atualizarClienteDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
