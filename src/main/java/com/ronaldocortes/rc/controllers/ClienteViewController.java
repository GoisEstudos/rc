package com.ronaldocortes.rc.controllers;

import com.ronaldocortes.rc.dtos.ClienteDtos.BuscarTodosClienteDTO;
import com.ronaldocortes.rc.dtos.ClienteDtos.CriarClienteDTO;
import com.ronaldocortes.rc.dtos.ClienteDtos.FiltroClienteDTO;
import com.ronaldocortes.rc.enuns.StatusCliente;
import com.ronaldocortes.rc.enuns.StatusFiltroCliente;
import com.ronaldocortes.rc.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("statusOptions", StatusCliente.values());
        return "cadastro-cliente";
    }

    @GetMapping("/lista")
    public String listarClientes(@ModelAttribute FiltroClienteDTO filtroClienteDTO, Model model) {
        List<BuscarTodosClienteDTO> clientes = clienteService.buscarClientes(filtroClienteDTO);
        model.addAttribute("clientes", clientes);
        model.addAttribute("filtro", filtroClienteDTO);
        model.addAttribute("statusOptions", StatusFiltroCliente.values());
        return "lista-clientes";
    }

    @PostMapping("/cadastro")
    public String cadastrarCliente(@ModelAttribute CriarClienteDTO criarClienteDTO, Model model) {
        try {
            validar(criarClienteDTO);

            CriarClienteDTO clienteCriado = clienteService.criarCliente(criarClienteDTO);

            model.addAttribute("nome", clienteCriado.nome());
            model.addAttribute("telefone", clienteCriado.telefone());
            model.addAttribute("status", clienteCriado.statusCliente());

            return "fragments/resultado-cadastro :: sucesso";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "fragments/resultado-cadastro :: erro";
        } catch (Exception e) {
            model.addAttribute("erro", "Não foi possível cadastrar o cliente. Tente novamente.");
            return "fragments/resultado-cadastro :: erro";
        }
    }

    private void validar(CriarClienteDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }
        if (dto.telefone() == null || !dto.telefone().matches("\\d{11}")) {
            throw new IllegalArgumentException("O telefone deve ter 11 dígitos, apenas números (DDD + número).");
        }
    }
}