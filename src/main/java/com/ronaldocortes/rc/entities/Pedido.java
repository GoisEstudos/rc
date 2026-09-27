package com.ronaldocortes.rc.entities;

import com.ronaldocortes.rc.enuns.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatusPedido status = StatusPedido.ATIVO;

    private BigDecimal valorTotal;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PedidoItem> itens = new ArrayList<>();

    public BigDecimal calcularValorTotal() {

        return itens.stream()
                .map(PedidoItem::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
