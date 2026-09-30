package com.ronaldocortes.rc.entities;

import com.ronaldocortes.rc.enuns.StatusCliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusCliente status = StatusCliente.ATIVO;

    @NotEmpty(message = "Nome não pode ser vazio ou null")
    private String nome;

    @NotEmpty(message = "Telefone não pode ser vazio ou null")
    private String telefone;

}
