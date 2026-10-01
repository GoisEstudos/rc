package com.ronaldocortes.rc.repositories;

import com.ronaldocortes.rc.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor<Movimentacao> {
    Optional<Movimentacao> findByPedidoId(Long pedidoId);
}
