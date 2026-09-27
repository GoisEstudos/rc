package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.ClienteDtos.*;
import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;
import com.ronaldocortes.rc.repositories.ClienteRepository;
import com.ronaldocortes.rc.specification.ClienteSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<BuscarTodosClienteDTO> buscarClientes(FiltroClienteDTO filtroClienteDTO) {
       return clienteRepository.findAll(ClienteSpecification.comFiltros(filtroClienteDTO)).stream().map(BuscarTodosClienteDTO::new).toList();
    }

    public BuscarClientePorIdDTO buscarClientePorId(Long id) {
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente Não Encontrado!"));

        return new BuscarClientePorIdDTO(newCliente);
    }

    public CriarClienteDTO criarCliente(CriarClienteDTO criarClienteDTO) {
        Cliente newCliente = new Cliente();

        newCliente.setNome(criarClienteDTO.nome());
        newCliente.setTelefone(criarClienteDTO.telefone());
        newCliente.setStatus(criarClienteDTO.statusCliente() != null ? criarClienteDTO.statusCliente() : StatusCliente.ATIVO);

        clienteRepository.save(newCliente);

        return new CriarClienteDTO(newCliente);
    }

    public AtualizarClienteDTO atualizarCliente(AtualizarClienteDTO atualizarClienteDTO, Long id) {
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não Encontrado!"));
        newCliente.setNome(atualizarClienteDTO.nome() != null ? atualizarClienteDTO.nome() : newCliente.getNome());

        newCliente.setTelefone(atualizarClienteDTO.telefone() != null ? atualizarClienteDTO.telefone() : newCliente.getTelefone());

        clienteRepository.save(newCliente);
        return new AtualizarClienteDTO(newCliente);
    }

    public DeletarClienteDTO deletarCliente(Long id) {
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não Encontrado!"));
        newCliente.setStatus(StatusCliente.INATIVO);
        clienteRepository.save(newCliente);

        return new DeletarClienteDTO(newCliente);
    }


}
