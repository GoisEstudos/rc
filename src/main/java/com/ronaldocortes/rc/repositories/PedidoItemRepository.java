package com.ronaldocortes.rc.repositories;

import com.ronaldocortes.rc.entities.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}
