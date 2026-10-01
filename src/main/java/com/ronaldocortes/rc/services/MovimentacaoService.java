package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.MovimentacaoDtos.*;
import com.ronaldocortes.rc.entities.Movimentacao;
import com.ronaldocortes.rc.enuns.OrigemMovimentacao;
import com.ronaldocortes.rc.enuns.StatusMovimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoEncontradaException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoPodeSerCanceladaException;
import com.ronaldocortes.rc.exceptions.MovimentacaoException.MovimentacaoNaoPodeSerEstornadaException;
import com.ronaldocortes.rc.exceptions.PedidoException.AlterarExcluirMovimentacaoPedidoException;
import com.ronaldocortes.rc.repositories.MovimentacaoRepository;
import com.ronaldocortes.rc.specification.MovimentacaoSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public BuscarMovimentacoesResponseDTO buscarTodasMovimentacoes(
            FiltroMovimentacaoDTO filtroMovimentacao
    ) {
        List<Movimentacao> movimentacoes =
                movimentacaoRepository.findAll(
                        MovimentacaoSpecification.comFiltro(filtroMovimentacao)
                );

        List<BuscarTodasMovimentacoesDTO> movimentacoesDTO =
                movimentacoes.stream()
                        .map(BuscarTodasMovimentacoesDTO::new)
                        .toList();

        List<Movimentacao> movimentacoesFinalizadas =
                movimentacoes.stream()
                        .filter(m -> m.getStatus() == StatusMovimentacao.FINALIZADA)
                        .toList();

        BigDecimal valorTotalEntrada = movimentacoesFinalizadas.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.ENTRADA)
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorTotalSaida = movimentacoesFinalizadas.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.SAIDA)
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldo = valorTotalEntrada.subtract(valorTotalSaida);

        ResumoMovimentacoesDTO resumo = new ResumoMovimentacoesDTO(
                valorTotalEntrada,
                valorTotalSaida,
                saldo
        );

        return new BuscarMovimentacoesResponseDTO(
                movimentacoesDTO,
                resumo
        );
    }

    public BuscarMovimentacaoPorIdDTO buscarMovimentacaoPorId(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id).orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));
        return new BuscarMovimentacaoPorIdDTO(movimentacao);
    }

    @Transactional
    public CriarMovimentacaoDTO criarMovimentacao(CriarMovimentacaoDTO criarMovimentacao) {
        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setValor(criarMovimentacao.valor());
        movimentacao.setData(LocalDateTime.now().withNano(0));
        movimentacao.setDescricao(criarMovimentacao.descricao());
        movimentacao.setTipo(criarMovimentacao.tipo());
        movimentacao.setOrigem(OrigemMovimentacao.MANUAL);
        movimentacao.setPedido(null);

        movimentacaoRepository.save(movimentacao);

        return new CriarMovimentacaoDTO(movimentacao);
    }

    @Transactional
    public AtualizarMoviementacaoDTO atualizarMoviementacao(AtualizarMoviementacaoDTO atualizarMoviementacao, Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id).orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));

        validarMovimentacaoManual(movimentacao);

        movimentacao.setValor(atualizarMoviementacao.valor() != null ? atualizarMoviementacao.valor() : movimentacao.getValor());
        movimentacao.setData(atualizarMoviementacao.data() != null ? atualizarMoviementacao.data() : movimentacao.getData());
        movimentacao.setDescricao(atualizarMoviementacao.descricao() != null ? atualizarMoviementacao.descricao() : movimentacao.getDescricao());
        movimentacao.setTipo(atualizarMoviementacao.tipo() != null ? atualizarMoviementacao.tipo() : movimentacao.getTipo());

        return new AtualizarMoviementacaoDTO(movimentacao);
    }

    @Transactional
    public void cancelarMovimentacao(Long movimentacaoId) {
        Movimentacao movimentacao = movimentacaoRepository.findById(movimentacaoId).orElseThrow(() -> new MovimentacaoNaoEncontradaException(movimentacaoId));

        if (movimentacao.getOrigem() != OrigemMovimentacao.MANUAL) {
            throw new MovimentacaoNaoPodeSerCanceladaException();
        }

        if (movimentacao.getStatus() != StatusMovimentacao.FINALIZADA) {
            throw new MovimentacaoNaoPodeSerEstornadaException();
        }

        movimentacao.setStatus(StatusMovimentacao.CANCELADA);

        movimentacaoRepository.save(movimentacao);
    }

    public static void validarMovimentacaoManual(Movimentacao movimentacao) {
        if (movimentacao.getOrigem() == OrigemMovimentacao.PEDIDO) {
            throw new AlterarExcluirMovimentacaoPedidoException();
        }
    }
}


