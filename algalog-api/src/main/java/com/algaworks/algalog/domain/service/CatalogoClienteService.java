package com.algaworks.algalog.domain.service;

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

    @Transactional
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Cliente> buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Cliente criar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("O e-mail inserido já está em uso por outro usuário.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public ResponseEntity<Cliente> atualizar(Long clienteId, Cliente cliente) {
        if (!clienteRepository.existsById(clienteId))
            return ResponseEntity.notFound().build();

        cliente.setId(clienteId);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @Transactional
    public ResponseEntity<Cliente> excluir(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.ok(cliente.get());
    }

}
