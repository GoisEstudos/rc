package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.itemDtos.*;
import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;
import com.ronaldocortes.rc.repositories.ItemRepository;
import com.ronaldocortes.rc.specification.ItemSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<BuscarTodosItemDTO> buscarTodosItem(FiltroItemDTO filtroItemDTO) {
        return itemRepository.findAll(ItemSpecification.comFiltro(filtroItemDTO)).stream().map(BuscarTodosItemDTO::new).toList();
    }

    public BuscarItemPorIdDTO buscarItemPorId(Long id) {
        Item newItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Não encontrado!"));

        return new BuscarItemPorIdDTO(newItem);
    }

    public CriarItemDTO criarItem(CriarItemDTO criarItemDTO) {
        Item newItem = new Item();

        newItem.setNomeItem(criarItemDTO.nomeItem());
        newItem.setPreco(criarItemDTO.preco());
        newItem.setStatus(criarItemDTO.status() != null ? criarItemDTO.status() : StatusItem.ATIVO);

        itemRepository.save(newItem);

        return new CriarItemDTO(newItem);
    }

    public AtualizarItemDTO atualizarItem(AtualizarItemDTO atualizarItemDTO, Long id) {
        Item newItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        newItem.setNomeItem(atualizarItemDTO.nomeItem() != null ? atualizarItemDTO.nomeItem() : newItem.getNomeItem());
        newItem.setPreco(atualizarItemDTO.preco() != null ? atualizarItemDTO.preco() : newItem.getPreco());

        itemRepository.save(newItem);

        return new AtualizarItemDTO(newItem);
    }

    public DeletarItemDTO deletarItem(Long id) {
        Item newItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não contrado!"));
        newItem.setStatus(StatusItem.INATIVO);
        itemRepository.save(newItem);

        return new DeletarItemDTO(newItem);
    }
}
