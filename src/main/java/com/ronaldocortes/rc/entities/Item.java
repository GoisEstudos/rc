package com.ronaldocortes.rc.entities;

import com.ronaldocortes.rc.enuns.StatusItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatusItem status = StatusItem.ATIVO;

    private String nomeItem;

    private BigDecimal preco;
}
