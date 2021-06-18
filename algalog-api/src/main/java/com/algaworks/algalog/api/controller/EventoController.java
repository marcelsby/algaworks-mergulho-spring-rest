package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.EventoMapper;
import com.algaworks.algalog.api.model.request.EventoInput;
import com.algaworks.algalog.api.model.response.EventoOutput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Evento;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroEventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/eventos")
public class EventoController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroEventoService registroEventoService;
    private EventoMapper eventoMapper;

    @GetMapping
    public List<EventoOutput> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return eventoMapper.toCollectionModel(entrega.getEventos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoOutput registrar(@PathVariable Long entregaId,
                                  @Valid @RequestBody EventoInput eventoInput) {
        Evento eventoRegistrado = registroEventoService
                .registrar(entregaId, eventoInput.getDescricao());

        return eventoMapper.toModel(eventoRegistrado);
    }

}
