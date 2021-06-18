package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.mapper.ClienteMapper;
import com.algaworks.algalog.api.model.request.ClienteInput;
import com.algaworks.algalog.api.model.response.ClienteOutput;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    public Cliente procurar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
    }

    @Transactional
    public List<ClienteOutput> listar() {
        List<ClienteOutput> listagemClientes = clienteMapper
                .toCollectionModel(clienteRepository.findAll());

        return listagemClientes;
    }

    @Transactional
    public ResponseEntity<ClienteOutput> buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> clienteMapper.toModel(cliente))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ClienteOutput criar(ClienteInput clienteInput) {
        boolean emailEmUso = clienteRepository.findByEmail(clienteInput.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(clienteInput));

        if (emailEmUso) {
            throw new NegocioException("O e-mail inserido já está em uso por outro usuário.");
        }

        Cliente novoCliente = clienteRepository.save(clienteMapper.toEntity(clienteInput));

        return clienteMapper.toModel(novoCliente);
    }

    @Transactional
    public ResponseEntity<ClienteOutput> atualizar(Long clienteId, ClienteInput clienteInput) {
        if (!clienteRepository.existsById(clienteId))
            return ResponseEntity.notFound().build();

        Cliente clienteAtualizado = clienteMapper.toEntity(clienteInput);

        clienteAtualizado.setId(clienteId);

        clienteRepository.save(clienteAtualizado);

        return ResponseEntity.ok(clienteMapper.toModel(clienteAtualizado));
    }

    @Transactional
    public ResponseEntity<ClienteOutput> excluir(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        ClienteOutput clienteDeletado = clienteMapper.toModel(clienteRepository
                .findById(clienteId).get());

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.ok(clienteDeletado);
    }

}
