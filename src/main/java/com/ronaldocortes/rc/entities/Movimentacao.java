package com.ronaldocortes.rc.entities;

import com.ronaldocortes.rc.enuns.OrigemMovimentacao;
import com.ronaldocortes.rc.enuns.StatusMovimentacao;
import com.ronaldocortes.rc.enuns.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusMovimentacao status;

    private BigDecimal valor;

    private LocalDateTime data;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @Enumerated(EnumType.STRING)
    private OrigemMovimentacao origem;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
