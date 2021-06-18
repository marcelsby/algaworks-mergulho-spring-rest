package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.EntregaMapper;
import com.algaworks.algalog.api.model.request.EntregaInput;
import com.algaworks.algalog.api.model.response.EntregaOutput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitarEntregaService;
    private EntregaMapper entregaMapper;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaRepository entregaRepository;

    @GetMapping
    public List<EntregaOutput> listar() {
            List<Entrega> entregas = entregaRepository.findAll();

            return entregaMapper.toCollectionModel(entregas);
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaOutput> buscar(@PathVariable Long entregaId) {
        ResponseEntity<EntregaOutput> entregaBuscada = entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());

        return entregaBuscada;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaOutput solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega entregaSolicitada = solicitarEntregaService.solicitar(entregaMapper.toEntity(entregaInput));

        return entregaMapper.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

}
