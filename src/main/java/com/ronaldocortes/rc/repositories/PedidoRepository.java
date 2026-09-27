package com.ronaldocortes.rc.repositories;

import com.ronaldocortes.rc.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
