package com.ronaldocortes.rc.controllers;

import com.ronaldocortes.rc.dtos.ItemDtos.*;
import com.ronaldocortes.rc.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<BuscarTodosItemDTO>> buscarTodosItem(@ModelAttribute FiltroItemDTO filtroItemDTO) {
        return ResponseEntity.status(200).body(itemService.buscarTodosItem(filtroItemDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarItemPorIdDTO> buscarItemPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(itemService.buscarItemPorId(id));
    }

    @PostMapping
    public ResponseEntity<CriarItemDTO> criarItem(@Valid @RequestBody CriarItemDTO criarItemDTO) {
        return ResponseEntity.status(201).body(itemService.criarItem(criarItemDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AtualizarItemDTO> atualizarItem(@RequestBody AtualizarItemDTO atualizarItemDTO, @PathVariable Long id) {
        return ResponseEntity.status(200).body(itemService.atualizarItem(atualizarItemDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }
}
