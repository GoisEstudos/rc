package com.ronaldocortes.rc.entities;

import com.ronaldocortes.rc.enuns.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDateTime data = LocalDateTime.now().withNano(0);

    private BigDecimal valorTotal;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PedidoItem> itens = new ArrayList<>();

    public BigDecimal calcularValorTotal() {

        this.valorTotal = itens.stream()
                .map(PedidoItem::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return this.valorTotal;
    }

    public void adicionarItem(PedidoItem item) {
        itens.add(item);
        item.setPedido(this);
    }

    public void removerItem(PedidoItem item) {
        itens.remove(item);
        item.setPedido(null);
    }
}
