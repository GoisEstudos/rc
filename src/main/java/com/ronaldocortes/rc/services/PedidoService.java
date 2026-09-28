package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.PedidoDtos.*;
import com.ronaldocortes.rc.dtos.PedidoItemDtos.CriarPedidoItemDTO;
import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.entities.Item;
import com.ronaldocortes.rc.entities.Pedido;
import com.ronaldocortes.rc.entities.PedidoItem;
import com.ronaldocortes.rc.enuns.StatusPedido;
import com.ronaldocortes.rc.repositories.ClienteRepository;
import com.ronaldocortes.rc.repositories.ItemRepository;
import com.ronaldocortes.rc.repositories.PedidoItemRepository;
import com.ronaldocortes.rc.repositories.PedidoRepository;
import com.ronaldocortes.rc.specification.PedidoSpecification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final ItemRepository itemRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(ItemRepository itemRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.itemRepository = itemRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<BuscarTodosPedidosDTO> buscarTodosItem(FiltroPedidoDTO filtroPedidoDTO) {
        return pedidoRepository.findAll(PedidoSpecification.comFiltro(filtroPedidoDTO)).stream().map(BuscarTodosPedidosDTO::new).toList();
    }

    public BuscarPedidoPorIdDTO buscarPedidoPorId(Long id) {
        Pedido newPedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido Não encontrado!"));
        return new BuscarPedidoPorIdDTO(newPedido);
    }

    public ResponseCriarPedidoDTO criarPedido(CriarPedidoDTO criarPedidoDTO) {
        Cliente newCliente = clienteRepository.findById(criarPedidoDTO.clienteId()).orElseThrow(() -> new RuntimeException("Cliente Não encontrado"));

        Pedido newPedido = new Pedido();
        newPedido.setCliente(newCliente);
        newPedido.setData(LocalDateTime.now());
        newPedido.setStatus(StatusPedido.ABERTO);

        for (CriarPedidoItemDTO itemDto : criarPedidoDTO.itens()) {
            Item item = itemRepository.findById(itemDto.itemId())
                    .orElseThrow(() -> new RuntimeException("Item Não encontrado!"));

            PedidoItem pedidoItem = new PedidoItem();

            pedidoItem.setItem(item);
            pedidoItem.setValorMilheiro(item.getPreco());
            pedidoItem.setQuantidade(itemDto.quantidade());
            pedidoItem.calcularSubtotal();
            newPedido.adicionarItem(pedidoItem);
        }

        newPedido.calcularValorTotal();
        pedidoRepository.save(newPedido);

        return new ResponseCriarPedidoDTO(newPedido);
    }

    public AtualizarPedidoDTO atualizarPedido(Long id) {
        Pedido newPedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        newPedido.setStatus(StatusPedido.FECHADO);
        pedidoRepository.save(newPedido);
        return new AtualizarPedidoDTO(newPedido);
    }

    public void deletarPedido(Long id) {
        Pedido newPedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido Não encontrado!"));

        pedidoRepository.delete(newPedido);
    }

}
