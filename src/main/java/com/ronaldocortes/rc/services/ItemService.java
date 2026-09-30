package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.ItemDtos.*;
import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.enuns.StatusItem;
import com.ronaldocortes.rc.exceptions.ItemException.ItemNaoEncontradoException;
import com.ronaldocortes.rc.repositories.ItemRepository;
import com.ronaldocortes.rc.specification.ItemSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));

        return new BuscarItemPorIdDTO(item);
    }

    @Transactional
    public CriarItemDTO criarItem(CriarItemDTO criarItemDTO) {
        Item item = new Item();

        item.setNomeItem(criarItemDTO.nomeItem());
        item.setPreco(criarItemDTO.preco());
        item.setStatus(criarItemDTO.status() != null ? criarItemDTO.status() : StatusItem.ATIVO);

        itemRepository.save(item);

        return new CriarItemDTO(item);
    }

    @Transactional
    public AtualizarItemDTO atualizarItem(AtualizarItemDTO atualizarItemDTO, Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));

        item.setNomeItem(atualizarItemDTO.nomeItem() != null ? atualizarItemDTO.nomeItem() : item.getNomeItem());
        item.setPreco(atualizarItemDTO.preco() != null ? atualizarItemDTO.preco() : item.getPreco());

        itemRepository.save(item);

        return new AtualizarItemDTO(item);
    }

    public void deletarItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));
        item.setStatus(StatusItem.INATIVO);
        itemRepository.save(item);
    }
}
