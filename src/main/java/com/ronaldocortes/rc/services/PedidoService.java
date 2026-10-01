package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.PedidoDtos.*;
import com.ronaldocortes.rc.dtos.PedidoItemDtos.CriarPedidoItemDTO;
import com.ronaldocortes.rc.entities.*;
import com.ronaldocortes.rc.enuns.OrigemMovimentacao;
import com.ronaldocortes.rc.enuns.StatusMovimentacao;
import com.ronaldocortes.rc.enuns.StatusPedido;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;
import com.ronaldocortes.rc.exceptions.ClienteException.ClienteNaoEncontradoException;
import com.ronaldocortes.rc.exceptions.ItemException.ItemNaoEncontradoException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoEncontradaException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoPodeSerCanceladaException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoPodeSerEstornadaException;
import com.ronaldocortes.rc.exceptions.PedidoException.PedidoNaoEncontradoException;
import com.ronaldocortes.rc.exceptions.PedidoException.PedidoNaoPodeSerCanceladoException;
import com.ronaldocortes.rc.exceptions.PedidoException.PedidoNaoPodeSerEstornadoException;
import com.ronaldocortes.rc.repositories.ClienteRepository;
import com.ronaldocortes.rc.repositories.ItemRepository;
import com.ronaldocortes.rc.repositories.MovimentacaoRepository;
import com.ronaldocortes.rc.repositories.PedidoRepository;
import com.ronaldocortes.rc.specification.PedidoSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.ronaldocortes.rc.services.MovimentacaoService.validarMovimentacaoManual;

@Service
public class PedidoService {

    private final ItemRepository itemRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public PedidoService(ItemRepository itemRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository, MovimentacaoRepository movimentacaoRepository) {
        this.itemRepository = itemRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<BuscarTodosPedidosDTO> buscarTodosItem(FiltroPedidoDTO filtroPedidoDTO) {
        return pedidoRepository.findAll(PedidoSpecification.comFiltro(filtroPedidoDTO)).stream().map(BuscarTodosPedidosDTO::new).toList();
    }

    public BuscarPedidoPorIdDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));
        return new BuscarPedidoPorIdDTO(pedido);
    }

    @Transactional
    public ResponseCriarPedidoDTO criarPedido(CriarPedidoDTO criarPedidoDTO) {
        Cliente newCliente = clienteRepository.findById(criarPedidoDTO.clienteId()).orElseThrow(() -> new ClienteNaoEncontradoException(criarPedidoDTO.clienteId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(newCliente);
        pedido.setData(LocalDateTime.now().withNano(0));
        pedido.setStatus(StatusPedido.ABERTO);

        for (CriarPedidoItemDTO itemDto : criarPedidoDTO.itens()) {
            Item item = itemRepository.findById(itemDto.itemId())
                    .orElseThrow(() -> new ItemNaoEncontradoException(itemDto.itemId()));

            PedidoItem pedidoItem = new PedidoItem();

            pedidoItem.setItem(item);
            pedidoItem.setValorMilheiro(item.getPreco());
            pedidoItem.setQuantidade(itemDto.quantidade());
            pedidoItem.calcularSubtotal();
            pedido.adicionarItem(pedidoItem);
        }

        pedido.calcularValorTotal();
        pedidoRepository.save(pedido);

        return new ResponseCriarPedidoDTO(pedido);
    }

    @Transactional
    public FecharPedidoDTO fecharPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));
        pedido.setStatus(StatusPedido.FECHADO);
        pedidoRepository.save(pedido);

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setValor(pedido.getValorTotal());
        movimentacao.setData(LocalDateTime.now().withNano(0));
        movimentacao.setDescricao("Recebimento referente ao pedido #" + pedido.getId());
        movimentacao.setTipo(TipoMovimentacao.ENTRADA);
        movimentacao.setOrigem(OrigemMovimentacao.PEDIDO);
        movimentacao.setPedido(pedido);

        movimentacaoRepository.save(movimentacao);

        return new FecharPedidoDTO(pedido);
    }

    @Transactional
    public void estornarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
        Movimentacao movimentacao = movimentacaoRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException(pedidoId));

        if (movimentacao.getOrigem() != OrigemMovimentacao.PEDIDO) {
            throw new MovimentacaoNaoPodeSerEstornadaException();
        }

        if (pedido.getStatus() != StatusPedido.FECHADO) {
            throw new PedidoNaoPodeSerEstornadoException();
        }

        if (movimentacao.getStatus() != StatusMovimentacao.FINALIZADA) {
            throw new MovimentacaoNaoPodeSerEstornadaException();
        }

        movimentacao.setStatus(StatusMovimentacao.ESTORNADA);

        pedido.setStatus(StatusPedido.ABERTO);

        movimentacaoRepository.save(movimentacao);
        pedidoRepository.save(pedido);
    }

}
