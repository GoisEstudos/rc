package com.ronaldocortes.rc.services;

import com.ronaldocortes.rc.dtos.ClienteDtos.*;
import com.ronaldocortes.rc.entities.Cliente;
import com.ronaldocortes.rc.enuns.StatusCliente;
import com.ronaldocortes.rc.exceptions.ClienteException.ClienteNaoEncontradoException;
import com.ronaldocortes.rc.repositories.ClienteRepository;
import com.ronaldocortes.rc.specification.ClienteSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return new BuscarClientePorIdDTO(newCliente);
    }

    @Transactional
    public CriarClienteDTO criarCliente(CriarClienteDTO criarClienteDTO) {
        Cliente newCliente = new Cliente();

        newCliente.setNome(criarClienteDTO.nome());
        newCliente.setTelefone(criarClienteDTO.telefone());
        newCliente.setStatus(criarClienteDTO.statusCliente() != null ? criarClienteDTO.statusCliente() : StatusCliente.ATIVO);

        clienteRepository.save(newCliente);

        return new CriarClienteDTO(newCliente);
    }

    @Transactional
    public AtualizarClienteDTO atualizarCliente(AtualizarClienteDTO atualizarClienteDTO, Long id) {
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        newCliente.setNome(atualizarClienteDTO.nome() != null ? atualizarClienteDTO.nome() : newCliente.getNome());

        newCliente.setTelefone(atualizarClienteDTO.telefone() != null ? atualizarClienteDTO.telefone() : newCliente.getTelefone());

        clienteRepository.save(newCliente);
        return new AtualizarClienteDTO(newCliente);
    }

    public void deletarCliente(Long id) {
        Cliente newCliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        newCliente.setStatus(StatusCliente.INATIVO);
        clienteRepository.save(newCliente);

    }


}
