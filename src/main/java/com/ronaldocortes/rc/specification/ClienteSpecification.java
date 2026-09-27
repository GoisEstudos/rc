package com.ronaldocortes.rc.specification;

import com.ronaldocortes.rc.dtos.ClienteDtos.FiltroClienteDTO;
import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;
import com.ronaldocortes.rc.enuns.StatusFiltroCliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    public static Specification<Cliente> comFiltros(FiltroClienteDTO filtro) {
        return statusContem(filtro.status())
                .and(nomeContem(filtro.nome()))
                .and(telefoneContem(filtro.telefone()));
    }

    private static Specification<Cliente> statusContem(
            StatusFiltroCliente statusFiltro) {

        return (root, query, cb) -> {

            if (statusFiltro == null) {
                return cb.equal(
                        root.get("status"),
                        StatusCliente.ATIVO
                );
            }

            if (statusFiltro == StatusFiltroCliente.TODOS) {
                return null;
            }

            StatusCliente status =
                    StatusCliente.valueOf(statusFiltro.name());

            return cb.equal(root.get("status"), status);
        };
    }

    private static Specification<Cliente> nomeContem(String nome) {

        return (root, query, cb) -> {

            if (nome == null || nome.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("nome")),
                    "%" + nome.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Cliente> telefoneContem(String telefone) {

        return (root, query, cb) -> {

            if (telefone == null || telefone.isBlank()) {
                return null;
            }

            return cb.like(
                    root.get("telefone"),
                    "%" + telefone + "%"
            );
        };
    }
}
