package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.model.request.ClienteInput;
import com.algaworks.algalog.api.model.response.ClienteOutput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<ClienteOutput> listar() {
        return catalogoClienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteOutput> buscar(@PathVariable Long clienteId) {
        return catalogoClienteService.buscar(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteOutput criar(@Valid @RequestBody ClienteInput clienteInput) {
        return catalogoClienteService.criar(clienteInput);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteOutput> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody ClienteInput clienteInput) {
        return catalogoClienteService.atualizar(clienteId, clienteInput);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<ClienteOutput> excluir(@PathVariable Long clienteId) {
        return catalogoClienteService.excluir(clienteId);
    }

}
